package com.electronwill.nightconfig.core.serde;

import java.lang.reflect.Field;
import java.util.List;
import java.util.IdentityHashMap;
import java.util.EnumMap;
import java.util.function.Supplier;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.core.serde.annotations.SerdeDefault;
import com.electronwill.nightconfig.core.serde.annotations.SerdePhase;

/**
 * Serializes Java objects to NightConfig configurations ({@link UnmodifiableConfig}, {@link Config}, etc.).
 *
 * <h2>Example</h2>
 *
 * Given a class like this:
 * <pre><code>
 * class Position {
 *     private final int x, y, z;
 *
 *     public Position(int x, int y, int z) {
 *         this.x=x; this.y=y; this.z=z;
 *     }
 * }
 * </code></pre>
 *
 * And an instance like this:
 * <pre><code>
 * Position pos = new Position(12, -20, 42);
 * </code></pre>
 *
 * You can serialize your instance of Position to a Config with:
 * <pre><code>
 * Config conf = new ObjectSerializer.standard().serializeFields(pos, Config::inMemory);
 * // result: SimpleConfig{x=12, y=-20, z=42}
 * </code></pre>
 *
 * It is also possible to serialize an object to an existing configuration,
 * which is especially handy if you are working with {@link FileConfig}s.
 * <pre><code>
 * FileConfig myFileConfig = ...; // your FileConfig here
 * new ObjectSerializer().standard().serializeFields(pos, myFileConfig);
 * // the FileConfig is modified with the serialization result
 * </code></pre>
 * <p>
 * Use {@link #builder()} or {@link #blankBuilder()} to precisely configure
 * the serialization process.
 */
public final class ObjectSerializer {
	/**
	 * Creates a new {@link ObjectSerializerBuilder} with some standard serializers
	 * already registered.
	 *
	 * @return a new builder
	 */
	public static ObjectSerializerBuilder builder() {
		return new ObjectSerializerBuilder(true);
	}

	/**
	 * Creates a new {@link ObjectSerializerBuilder} with some standard serializers
	 * already registered.
	 *
	 * @return a new builder
	 */
	public static ObjectSerializerBuilder blankBuilder() {
		return new ObjectSerializerBuilder(false);
	}

	/**
	 * Creates a new {@link ObjectSerializer} with the standard deserializers.
	 * <p>
	 * This is equivalent to {@code ObjectSerializer.builder().build()}.
	 */
	public static ObjectSerializer standard() {
		return builder().build();
	}

	private final IdentityHashMap<Class<?>, ValueSerializer<?, ?>> classBasedSerializers;
	private final List<ValueSerializerProvider<?, ?>> generalProviders;

	/** the last-resort serializer provider, used when no other provider matches */
	private final ValueSerializerProvider<?, ?> defaultProvider;

	/** setting: skip transient fields as requested by the modifier */
	final boolean applyTransientModifier;

	ObjectSerializer(ObjectSerializerBuilder builder) {
		this.classBasedSerializers = builder.classBasedSerializers;
		this.generalProviders = builder.generalProviders;
		this.defaultProvider = builder.defaultProvider;
		this.applyTransientModifier = builder.applyTransientModifier;
		assert classBasedSerializers != null && generalProviders != null && defaultProvider != null;
	}

	/**
	 * Serializes a single value.
	 * The resulting object can be a Config, a List, or a plain value that would be
	 * suitable for insertion in a
	 * configuration supplied by {@code configSupplier}.
	 * <p>
	 * To ensure that you serialize the <em>fields</em> of a Java object into a
	 * {@code Config}, use
	 * {@link #serializeFields(Object, Supplier)}.
	 *
	 * @return the object, converted to a config value
	 */
	public Object serialize(Object value, Supplier<? extends Config> configSupplier) {
		SerializerContext ctx = new SerializerContext(
				this,
				() -> configSupplier.get().configFormat(),
				configSupplier);
		return ctx.serializeValue(value);
	}

	/**
	 * Serializes an object as a {@code Config} or type {@code C} by transforming
	 * its fields into configuration
	 * entries. A new configuration is created with the {@code configSupplier}.
	 * <p>
	 * The serialization depends on the registered {@link ValueSerializer value
	 * serializers}.
	 *
	 * @see ObjectSerializerBuilder#withSerializerForClass(Class, ValueSerializer)
	 * @see ObjectSerializerBuilder#withSerializerForExactClass(Class,
	 *      ValueSerializer)
	 * @see ObjectSerializerBuilder#withSerializerProvider(ValueSerializerProvider)
	 *
	 * @return the new configuration
	 */
	public <C extends Config> C serializeFields(Object source, Supplier<C> configSupplier) {
		C dest = configSupplier.get();
		serializeFields(source, dest);
		return dest;
	}

	/**
	 * Serializes an object as a {@code Config} by transforming its fields into
	 * configuration entries.
	 * The entries are inserted in {@code destination}.
	 * <p>
	 * The serialization depends on the registered {@link ValueSerializer value
	 * serializers}.
	 *
	 * @param source      object to serialize
	 * @param destination configuration to store the result in
	 *
	 * @see ObjectSerializerBuilder#withSerializerForClass(Class, ValueSerializer)
	 * @see ObjectSerializerBuilder#withSerializerForExactClass(Class,
	 *      ValueSerializer)
	 * @see ObjectSerializerBuilder#withSerializerProvider(ValueSerializerProvider)
	 */
	public void serializeFields(Object source, Config destination) {
		// the destination exists, convert the fields recursively
		SerializerContext ctx = new SerializerContext(
				this,
				() -> destination.configFormat(),
				() -> destination.createSubConfig());
		ctx.serializeFields(source, destination);
	}

	/**
	 * Finds a suitable converter for this value.
	 *
	 * @throws SerdeException if no converter is found
	 */
	@SuppressWarnings("unchecked")
	<T, R> ValueSerializer<T, R> findValueSerializer(Object value, SerializerContext ctx) {
		Class<?> valueClass = value == null ? null : value.getClass();
		ValueSerializer<?, ?> maybeSe = null;
		for (ValueSerializerProvider<?, ?> provider : generalProviders) {
			maybeSe = provider.provide(valueClass, ctx);
			if (maybeSe != null) {
				return (ValueSerializer<T, R>) maybeSe;
			}
		}
		maybeSe = classBasedSerializers.get(valueClass);
		if (maybeSe != null) {
			return (ValueSerializer<T, R>) maybeSe;
		}
		maybeSe = defaultProvider.provide(valueClass, ctx);
		if (maybeSe != null) {
			return (ValueSerializer<T, R>) maybeSe;
		}
		throw ObjectSerializer.noSerializerFound(value, valueClass, ctx);
	}

	Supplier<?> findDefaultValueSupplier(Object fieldValue, Field field, Object instance) {
		EnumMap<SerdeDefault.WhenValue, SerdeDefault> defaultForSerializing = AnnotationProcessor
				.getConfigDefaultAnnotations(field)
				.get(SerdePhase.SERIALIZING);

		if (defaultForSerializing == null) {
			return null; // no default
		}

		SerdeDefault applicableDefault = null;
		// the field always exist, so there is no case for a "missing value"
		if (fieldValue == null) {
			// null value
			applicableDefault = defaultForSerializing.get(SerdeDefault.WhenValue.IS_NULL);
		} else {
			// avoid to call Util.isEmpty() if there's no default for empty values
			SerdeDefault forEmpty = defaultForSerializing.get(SerdeDefault.WhenValue.IS_EMPTY);
			if (forEmpty != null && Util.isEmpty(fieldValue)) {
				applicableDefault = forEmpty;
			}
		}
		// later: more cases?

		if (applicableDefault == null) {
			return null; // no applicable default for our config value
		}

		return AnnotationProcessor.resolveConfigDefaultProvider(applicableDefault, instance);
	}

	static SerdeException noSerializerFound(Object value, Class<?> valueClass,
			SerializerContext ctx) {
		ConfigFormat<?> format = ctx.configFormat();
		String supportedStr;
		if (format == null) {
			supportedStr = "The current SerializerContext has no ConfigFormat. Is there a bug in the implementation of the chosen Config type?";
		} else if (format.supportsType(valueClass)) {
			supportedStr = "The value's type is supported by the ConfigFormat of the current SerializerContext.";
		} else {
			supportedStr = "The value's type is NOT supported by the ConfigFormat of the current SerializerContext.";
		}
		String ofTypeStr = valueClass == null ? "" : " of type " + valueClass;
		return new SerdeException(
				"No suitable serializer found for value" + ofTypeStr + ": " + value + ". "
						+ supportedStr);
	}
}

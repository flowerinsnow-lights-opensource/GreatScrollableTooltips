package com.electronwill.nightconfig.core;

import com.electronwill.nightconfig.core.io.ConfigParser;
import com.electronwill.nightconfig.core.io.ConfigWriter;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * The commented version of {@link InMemoryFormat}
 *
 * @author TheElectronWill
 */
public class InMemoryCommentedFormat implements ConfigFormat<CommentedConfig> {
	private static final InMemoryCommentedFormat DEFAULT_INSTANCE = new InMemoryCommentedFormat(InMemoryFormat.DEFAULT_PREDICATE);
	private static final InMemoryCommentedFormat UNIVERSAL_INSTANCE = new InMemoryCommentedFormat(t -> true);

	public static InMemoryCommentedFormat defaultInstance() {
		return DEFAULT_INSTANCE;
	}

	public static InMemoryCommentedFormat withSupport(Predicate<Class<?>> supportPredicate) {
		return new InMemoryCommentedFormat(supportPredicate);
	}

	public static InMemoryCommentedFormat withUniversalSupport() {
		return UNIVERSAL_INSTANCE;
	}

	private final Predicate<Class<?>> supportPredicate;

	private InMemoryCommentedFormat(Predicate<Class<?>> supportPredicate) {
		this.supportPredicate = supportPredicate;
	}

	@Override
	public ConfigWriter createWriter() {
		throw new UnsupportedOperationException(
			"In memory configurations aren't meant to be written.");
	}

	@Override
	public ConfigParser<CommentedConfig> createParser() {
		throw new UnsupportedOperationException(
			"In memory configurations aren't meant to be parsed.");
	}

	@Override
	public CommentedConfig createConfig(Supplier<Map<String, Object>> mapCreator) {
		return CommentedConfig.of(mapCreator, this);
	}

	@Override
	public boolean supportsComments() {
		return true;
	}

	@Override
	public boolean supportsType(Class<?> type) {
		return supportPredicate.test(type);
	}

	@Override
	public boolean isInMemory() {
		return true;
	}
}
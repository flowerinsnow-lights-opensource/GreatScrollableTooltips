package com.electronwill.nightconfig.core.utils;

import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.UnmodifiableConfig;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author TheElectronWill
 */
public abstract class UnmodifiableConfigWrapper<C extends UnmodifiableConfig>
		implements UnmodifiableConfig {
	protected final C config;

	protected UnmodifiableConfigWrapper(C config) {
		this.config = Objects.requireNonNull(config, "The wrapped config must not be null");
	}

	@Override
	public <T> T getRaw(List<String> path) {
		return config.getRaw(path);
	}

	@Override
	public Map<String, Object> valueMap() {
		return config.valueMap();
	}

	@Override
	public Set<? extends Entry> entrySet() {
		return config.entrySet();
	}

	@Override
	public boolean contains(List<String> path) {
		return config.contains(path);
	}

	@Override
	public int size() {
		return config.size();
	}

	@Override
	public boolean isEmpty() {
		return config.isEmpty();
	}

	@Override
	public boolean equals(Object obj) {
		return config.equals(obj);
	}

	@Override
	public int hashCode() {
		return config.hashCode();
	}

	@Override
	public ConfigFormat<?> configFormat() {
		return config.configFormat();
	}
}

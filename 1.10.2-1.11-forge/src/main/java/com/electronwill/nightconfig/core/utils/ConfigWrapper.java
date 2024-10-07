package com.electronwill.nightconfig.core.utils;

import com.electronwill.nightconfig.core.Config;

import java.util.List;
import java.util.Set;

/**
 * @author TheElectronWill
 */
public abstract class ConfigWrapper<C extends Config> extends UnmodifiableConfigWrapper<C>
		implements Config {

	protected ConfigWrapper(C config) {
		super(config);
	}

	@Override
	public Set<? extends Config.Entry> entrySet() {
		return config.entrySet();
	}

	@Override
	public <T> T set(List<String> path, Object value) {
		return config.set(path, value);
	}

	@Override
	public boolean add(List<String> path, Object value) {
		return config.add(path, value);
	}

	@Override
	public <T> T remove(List<String> path) {
		return config.remove(path);
	}

	@Override
	public void clear() {
		config.clear();
	}

	@Override
	public Config createSubConfig() {
		return config.createSubConfig();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ':' + config;
	}
}
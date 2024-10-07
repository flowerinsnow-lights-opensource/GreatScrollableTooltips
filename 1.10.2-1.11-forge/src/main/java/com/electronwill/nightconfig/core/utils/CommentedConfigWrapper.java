package com.electronwill.nightconfig.core.utils;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author TheElectronWill
 */
public abstract class CommentedConfigWrapper<C extends CommentedConfig> extends ConfigWrapper<C>
		implements CommentedConfig {

	protected CommentedConfigWrapper(C config) {
		super(config);
	}

	@Override
	public String getComment(List<String> path) {
		return config.getComment(path);
	}

	@Override
	public boolean containsComment(List<String> path) {
		return config.containsComment(path);
	}

	@Override
	public String setComment(List<String> path, String comment) {
		return config.setComment(path, comment);
	}

	@Override
	public String removeComment(List<String> path) {
		return config.removeComment(path);
	}

	@Override
	public Map<String, String> commentMap() {
		return config.commentMap();
	}

	@Override
	public Set<? extends CommentedConfig.Entry> entrySet() {
		return config.entrySet();
	}

	@Override
	public void clearComments() {
		config.clearComments();
	}

	@Override
	public void putAllComments(Map<String, CommentNode> comments) {
		config.putAllComments(comments);
	}

	@Override
	public void putAllComments(UnmodifiableCommentedConfig commentedConfig) {
		config.putAllComments(commentedConfig);
	}

	@Override
	public Map<String, CommentNode> getComments() {
		return config.getComments();
	}

	@Override
	public CommentedConfig createSubConfig() {
		return config.createSubConfig();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ':' + config;
	}
}
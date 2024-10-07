package com.electronwill.nightconfig.core;

import com.electronwill.nightconfig.core.concurrent.ConcurrentConfig;
import com.electronwill.nightconfig.core.utils.FakeUnmodifiableCommentedConfig;

import java.util.*;

import static com.electronwill.nightconfig.core.utils.StringUtils.split;

/**
 * An unmodifiable config that supports comments.
 *
 * @author TheElectronWill
 */
public interface UnmodifiableCommentedConfig extends UnmodifiableConfig {
	/**
	 * Gets a comment from the config.
	 *
	 * @param path the comment's path, each part separated by a dot. Example "a.b.c"
	 * @return the comment at the given path, or {@code null} if there is none.
	 */
	default String getComment(String path) {
		return getComment(split(path, '.'));
	}

	/**
	 * Gets a comment from the config.
	 *
	 * @param path the comment's path, each element of the list is a different part of the path.
	 * @return the comment at the given path, or {@code null} if there is none.
	 */
	String getComment(List<String> path);

	/**
	 * Gets an optional comment from the config.
	 *
	 * @param path the comment's path, each part separated by a dot. Example "a.b.c"
	 * @return an Optional containing the comment at the given path, or {@code Optional.empty()} if
	 * there is no such comment.
	 */
	default Optional<String> getOptionalComment(String path) {
		return getOptionalComment(split(path, '.'));
	}

	/**
	 * Gets an optional comment from the config.
	 *
	 * @param path the comment's path, each element of the list is a different part of the path.
	 * @return an Optional containing the comment at the given path, or {@code Optional.empty()} if
	 * there is no such comment.
	 */
	default Optional<String> getOptionalComment(List<String> path) {
		return Optional.ofNullable(getComment(path));
	}

	/**
	 * Checks if the config contains a comment at some path.
	 *
	 * @param path the path to check, each part separated by a dot. Example "a.b.c"
	 * @return {@code true} if the path is associated with a comment, {@code false} if it's not.
	 */
	default boolean containsComment(String path) {
		return containsComment(split(path, '.'));
	}

	/**
	 * Checks if the config contains a comment at some path.
	 *
	 * @param path the path to check, each element of the list is a different part of the path.
	 * @return {@code true} if the path is associated with a comment, {@code false} if it's not.
	 */
	boolean containsComment(List<String> path);

	/**
	 * Returns a Map view of the config's comments. If the config is unmodifiable then the returned
	 * map is unmodifiable too.
	 * <p>
	 * The comment map contains only the comments of the direct elements of the
	 * configuration, not the comments of their sub-elements.
	 *
	 * @return a Map view of the config's comments.
	 * @deprecated {@code commentMap()} may not be supported by some config types, in
	 *             particular {@link ConcurrentConfig}, and may be removed in a future version.
	 * 			   Prefer to use {@link #entrySet()} instead.
	 */
	@Deprecated
	Map<String, String> commentMap();

	/**
	 * Returns a Map containing a deep copy of all the comments in the config.
	 *
	 * @return a Map containing the comments in the config.
	 */
	default Map<String, CommentNode> getComments() {
		Map<String, CommentNode> map = new HashMap<>();
		getComments(map);
		return map;
	}

	/**
	 * Puts all the config's comments to the specified map.
	 *
	 * @param destination the map where to put the comments.
	 */
	default void getComments(Map<String, CommentNode> destination) {
		for (Entry entry : entrySet()) {
			String key = entry.getKey();
			String comment = entry.getComment();
			Object value = entry.getValue();
			if (comment != null || value instanceof UnmodifiableCommentedConfig) {
				Map<String, CommentNode> children = (value instanceof UnmodifiableCommentedConfig)
													? ((UnmodifiableCommentedConfig)value).getComments()
													: null;
				CommentNode node = new CommentNode(comment, children);
				destination.put(key, node);
			}
		}
	}

	final class CommentNode {
		private final String comment;
		private final Map<String, CommentNode> children;

		/**
		 * Creates a new CommentNode.
		 * <p>
		 * Note: The comment and children are never both null.
		 *
		 * @param comment  the comment, may be null
		 * @param children the children Map, may be null
		 */
		public CommentNode(String comment, Map<String, CommentNode> children) {
			if (comment == null && children == null) {
				throw new IllegalArgumentException("There is no point in creating a CommentNode "
												   + "if the comment AND the children are null.");
			}
			this.comment = comment;
			this.children = children;
		}

		/**
		 * @return the node's comment
		 */
		public String getComment() {
			return comment;
		}

		/**
		 * @return the Map of the node's children
		 */
		public Map<String, CommentNode> getChildren() {
			return children;
		}
	}

	@Override
	Set<? extends Entry> entrySet();

	/**
	 * An unmodifiable commented config entry.
	 */
	interface Entry extends UnmodifiableConfig.Entry {
		/**
		 * @return the entry's comment, may contain several lines
		 */
		String getComment();
	}

	/**
	 * If the specified config is an instance of UnmodifiableCommentedConfig, returns it. Else,
	 * returns a "fake" UnmodifiableCommentedConfig instance with the same values (ie the valueMaps
	 * are equal) as the config. This fake UnmodifiableCommentedConfig doesn't actually store nor
	 * process comments, it just provides the methods of UnmodifiableCommentedConfig.
	 *
	 * @param config the config
	 * @return an UnmodifiableCommentedConfig instance backed by the specified config
	 */
	static UnmodifiableCommentedConfig fake(UnmodifiableConfig config) {
		if (config instanceof UnmodifiableCommentedConfig) {
			return (UnmodifiableCommentedConfig)config;
		}
		return new FakeUnmodifiableCommentedConfig(config);
	}
}

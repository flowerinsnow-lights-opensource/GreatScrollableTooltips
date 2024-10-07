package com.electronwill.nightconfig.core.utils;

import java.io.Reader;
import java.util.Objects;

/**
 * A fast, unsynchronized, simple Reader that reads the content of a String.
 *
 * @author TheElectronWill
 */
public final class FastStringReader extends Reader {
	private final String str;
	private final int lim;
	private int cursor = 0, mark;

	public FastStringReader(String str, int lim) {
		if (lim > str.length() || lim < 0) {
			throw new IllegalArgumentException("Invalid limit " + lim + ": must be >= 0 and < str.length()");
		}
		this.str = Objects.requireNonNull(str, "The string must not be null.");
		this.lim = lim;
	}

	public FastStringReader(String str) {
		this.str = Objects.requireNonNull(str, "The string must not be null.");
		this.lim = str.length();
	}

	@Override
	public int read() {
		return cursor < lim ? str.charAt(cursor++) : -1;
	}

	@Override
	public int read(char[] cbuf, int off, int len) {
		if (cursor == lim) {
			return -1;
		}
		len = Math.min(len, lim - cursor);
		int srcEnd = cursor + len;
		str.getChars(cursor, srcEnd, cbuf, off);
		cursor = srcEnd;
		return len;
	}

	@Override
	public long skip(long n) {
		int skip = (int)n;
		cursor += skip;
		return skip;
	}

	@Override
	public boolean markSupported() {
		return true;
	}

	@Override
	public void mark(int readAheadLimit) {
		mark = cursor;
	}

	@Override
	public void reset() {
		cursor = mark;
	}

	@Override
	public boolean ready() {
		return true;
	}

	@Override
	public void close() {}
}

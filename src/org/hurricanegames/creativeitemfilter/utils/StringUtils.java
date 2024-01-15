package org.hurricanegames.creativeitemfilter.utils;

public class StringUtils {

	public static String clampString(String string, int limit) {
		return string.length() < limit ? string : string.substring(0, limit);
	}

	public static String filterValidCharacters(String input) {
		return input.replaceAll("[^a-zA-Zа-яА-Я0-9\\s.,;:!?'\"()\\[\\]{}@#&*-+/\\\\]", "");
	}
}

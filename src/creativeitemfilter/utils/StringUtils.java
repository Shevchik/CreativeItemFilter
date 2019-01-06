package creativeitemfilter.utils;

public class StringUtils {

	public static String clampString(String string) {
		return clampString(string, 64);
	}

	public static String clampString(String string, int limit) {
		return string.length() < limit ? string : string.substring(0, limit);
	}

}

package hust.soict.cysec.oop.common;

public class StringUtility {
	public static boolean isEmptyString(String s) {
		return s == null || s.isEmpty() || s.isBlank();
	}
}

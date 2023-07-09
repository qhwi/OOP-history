package hust.soict.cysec.oop.common;

public class StringUtility {
	public static boolean checkEmptyString(String s) {
		return s != null && !s.isEmpty() && !s.isBlank();
	}
}

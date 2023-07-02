package hust.soict.cysec.oop.crawler.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtility {
	public static ArrayList<String> extractNumbersFromString(String regex, String str) {
		ArrayList<String> arr = new ArrayList<String>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while(m.find()) {
			arr.add(m.group());
		}
		
		return arr;
	}
}

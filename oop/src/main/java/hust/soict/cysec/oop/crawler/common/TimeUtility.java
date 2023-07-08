package hust.soict.cysec.oop.crawler.common;

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
	
	public static ArrayList<String> extractYearRangeFromTitle(String str) {
		// String in format of "Some event (-1000 - 1000)" or "Some event (200 TCN)"
		str = str.strip();
		String title = str;
		String time = null;
		String startTime = "?";
		String endTime = "?";
		Matcher year_matcher = Pattern.compile("\\(([^)]+)\\)[^()]*$").matcher(str);
		ArrayList<String> ret = new ArrayList<String>();
		
		if (year_matcher.find()) {
			time = year_matcher.group(1).replace("–", "-");
			if (!time.contains(" - ")) {
				startTime = time.replace(" ", "");
				if (startTime.contains("-"))
					startTime = startTime.replace("-", "") + " TCN";
			}
			else {
				String[] arr = time.split(" - ", 2);
				startTime = arr[0].replace(" ", "");
				if (startTime.contains("-"))
					startTime = startTime.replace("-", "") + " TCN";
				if (!startTime.matches(".*\\d.*"))
					startTime = "?";
				endTime = arr[1].replace(" ", "");
				if (endTime.contains("-"))
					endTime = endTime.replace("-", "") + " TCN";
			}
			if (!startTime.matches(".*\\d.*"))
				startTime = "?";
			if (!endTime.matches(".*\\d.*"))
				endTime = "?";
			title = str.replaceAll("\\s*\\([^)]+\\)", "");
		}
		ret.add(title);
		ret.add(startTime);
		ret.add(endTime);
		return ret;
	}
}

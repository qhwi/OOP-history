package hust.soict.cysec.oop.crawler.historicalfigure.figure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.model.Figure;

public class FigureGiaoducNet {
	public static List<Figure> crawlAll() throws IOException {
		List<Figure> figures = new ArrayList<>();
		
		String url = "https://giaoduc.net.vn/nhung-duong-pho-mang-name-cac-vi-tuong-linh-va-si-quan-quan-doi-post173227.gd";
		Document doc = Jsoup.connect(url).get();
		
		System.out.println("Crawl: " + url);
		Element mainBody = doc.getElementsByClass("details__content cms-body ").first();
		Elements paragraphs = mainBody.select("p");

		Elements hasNameparagraphs = mainBody.select("p:has(em)");
		List<Element> figureParagraph = hasNameparagraphs.subList(2, hasNameparagraphs.size());
		for (Element p : figureParagraph) {
			Figure figure = new Figure();
			
			String name = p.select("strong").first().text();
			figure.setName(name);
			name = name.concat(" (");
			
			Element foundParagraph = findElement(name, paragraphs);
			if (foundParagraph != null) {
				String data = foundParagraph.text();
				
				int index = data.indexOf(name);
				String trimData = data.substring(index);
				figure.setNote(trimData);
				
				String homeTown = findHomeTown(trimData);
				figure.setHometown(homeTown);
				
				String dob = findDob(trimData); // only contains birth year and death year
				index = dob.indexOf("-");
				
				String birth = dob.substring(0, index);
				figure.setBirth(birth);
				
				String death = dob.substring(index + 1);
				figure.setDeath(death);
				
				name = name.substring(0, name.length() - 2);

				figure.setNote(trimData);
				
				figures.add(figure);
			}
		}
		
		return figures;
	}
	
	private static Element findElement(String findStr, List<Element> figureElement) {
		for (Element p : figureElement) {
			String text = p.text();
			if (text.contains(findStr)) {
				return p;
			}
		}
		return null;
	}
	
	private static String findDob(String data) {
		int start = data.indexOf("(");
		int end = data.indexOf(")");
		if (start != -1 && end != -1) {
			String dob = data.substring(start + 1, end);
			return dob;
		}
		return null;
	}
	
	private static String findHomeTown(String data) {
		if (data.contains("Hoàng Sâm")) {
			int start = data.indexOf("Quê");
			String homeTown = data.substring(start);
			return homeTown;
		} else if (data.contains("Nguyễn Phúc Lai")) {
			int start = data.indexOf("quê");
			String mainData = data.substring(start);
			int end = mainData.indexOf(".");
			String homeTown = mainData.substring(0, end);
			return homeTown;
		}
		int start = data.indexOf("quê");
		String mainData = data.substring(start);
		int end = mainData.indexOf(";");
		String homeTown = mainData.substring(0, end);
		return homeTown;
	}
}

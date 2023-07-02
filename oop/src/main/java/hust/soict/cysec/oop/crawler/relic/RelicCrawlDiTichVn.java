package hust.soict.cysec.oop.crawler.relic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.model.Figure;
import hust.soict.cysec.oop.model.Relic;

/*
 * Crawl 4300 relics
 */
public class RelicCrawlDiTichVn {	
	public static final String JSON_RELIC_PATH_DITICHVN = "C:\\Users\\triet\\eclipse-workspace\\TestCrawler\\src\\crawler\\json\\relics_ditichvn.json";
	
	public static String LOCATION_IDENTIFIER = "Vị trí: ";
	public static String TYPE_IDENTIFIER = "Loại hình di tích: ";
	public static String RANK_IDENTIFIER = "Xếp hạng: ";
	public static String WORSHIPED_IDENTIFIER = "Đối tượng thờ: ";
	
	private RelicCrawlDiTichVn() {};
	
	public static List<Relic> crawAll() throws IOException{
		String url = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId=";
		List<Relic> relics = new ArrayList<>();
		
		// i from 1865 -> 6193
		for(int i = 1865; i <= 6193; i++) {
			relics.add(crawlOnePage(url + i));
		}
		
		return relics;
	}
	
	private static Relic crawlOnePage(String url) throws IOException {
		Relic relic = new Relic();
		System.out.println("[RELIC CRAWLER] url: " + url);
		Document doc = Jsoup.connect(url).get();

		StringBuilder worshiped = new StringBuilder();
		StringBuilder rank = new StringBuilder();
		StringBuilder type = new StringBuilder();
		
		String name = doc.select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section > h2").text();
		String location = doc.select("#block-harvard-content > article > div > section > div > div.hl__library-info__sidebar > div:nth-child(1) > section > div > div > div.hl__contact-info__address > span").text();
		Elements info = doc.select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section > div div.hl__illustrated-list__list-item");
		
		for (org.jsoup.nodes.Element e: info) {
			if (!e.text().equals("")) {
				if (e.text().contains(TYPE_IDENTIFIER)) {
					type.append(e.text());
				}else if (e.text().contains(RANK_IDENTIFIER)){
					rank.append(e.text());
				}else if (e.text().contains(WORSHIPED_IDENTIFIER)) {
					worshiped.append(e.text().replace(WORSHIPED_IDENTIFIER, ""));
				}
			}
		}
		
		if(name.length() == 0)
			return null;
		else
			relic.setName(name);
		if(location.length() != 0) {
			relic.setLocation(location.replace(LOCATION_IDENTIFIER, ""));
		}
		if(type.length() != 0) {
			relic.setType(type.toString().replace(TYPE_IDENTIFIER, ""));
		}
		if(rank.length() != 0) {
			relic.setRank(rank.toString().replace(RANK_IDENTIFIER, ""));
		}
		if(worshiped.length() != 0) {
			Figure figure = new Figure();
			figure.setName(worshiped.toString());
			relic.addFigure(figure);
		}

		// relic.print();
		
		return relic;
	}
}
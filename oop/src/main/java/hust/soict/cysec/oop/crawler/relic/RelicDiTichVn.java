package hust.soict.cysec.oop.crawler.relic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.LeafCrawler;
import hust.soict.cysec.oop.model.Relic;

/*
 * Crawl 4300 relics
 */
public class RelicDiTichVn extends LeafCrawler<Relic> {	
	public RelicDiTichVn() {
		super(Constants.JSON_RELIC);
	}
	
	public static String LOCATION_IDENTIFIER = "Vị trí: ";
	public static String TYPE_IDENTIFIER = "Loại hình di tích: ";
	public static String RANK_IDENTIFIER = "Xếp hạng: ";
	public static String WORSHIPED_IDENTIFIER = "Đối tượng thờ: ";
	
	@Override
	public List<Relic> crawl() throws IOException{
		String url = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId=";
		List<Relic> relics = new ArrayList<>();
		
		// i from 1865 -> 6193
		for(int i = 1865; i <= 6193; i++) {
			Relic relic = crawlOnePage(url + i);
			if(relic != null) {
				relics.add(relic);
			}
		}
		
		return relics;
	}
	
	private static Relic crawlOnePage(String url) throws IOException {
		Relic relic = new Relic();
		System.out.println("[CRAWL RELIC] " + url);
		Document doc = Jsoup.connect(url).get();

		StringBuilder worshiped = new StringBuilder();
		StringBuilder rank = new StringBuilder();
		StringBuilder type = new StringBuilder();
		
		String name = doc.select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section > h2").text();
		if(name.isEmpty()) {
			return null;
		}
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
			relic.addFigure(worshiped.toString());
		}

		// relic.print();
		
		return relic;
	}
}
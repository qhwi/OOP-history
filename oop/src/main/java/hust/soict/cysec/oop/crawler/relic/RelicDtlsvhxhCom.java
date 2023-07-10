package hust.soict.cysec.oop.crawler.relic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.LeafCrawler;
import hust.soict.cysec.oop.model.Relic;

public class RelicDtlsvhxhCom extends LeafCrawler<Relic> {
	public RelicDtlsvhxhCom() {
		super(Constants.JSON_RELIC);
	}
	
	@Override
	public List<Relic> crawl() throws IOException{
		List<Relic> relics = new ArrayList<Relic>();
		String pageCrawlUrl = "http://ditichlichsu-vanhoahanoi.com/category/2dtlsvh/page/";
		List<String> pages = new ArrayList<String>();
		// Page from 1 to 44
		for(int i = 1; i <= 44; i++) {
			List<String> continueReadingLinks = crawlUrlFromPage(pageCrawlUrl + i);
			for (String link : continueReadingLinks) {
				pages.add(link);
			}
		}
		
		for (String page : pages) {
			relics.add(crawlOnePage(page));
		}
		
		return relics;
	}
	
	private static List<String> crawlUrlFromPage(String url) throws IOException {
		System.out.println("[CRAWL RELIC] " + url);
		List<String> continueReadingLinks = new ArrayList<String>();
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("div#post-wrapper > div > article > div.read-more > a");
		
		for (Element a : links) {
			String href = a.attr("href");
			continueReadingLinks.add(href);
		}
		
		return continueReadingLinks;
	}
	
	private static Relic crawlOnePage(String url) throws IOException {
		System.out.println("Crawl: " + url);
		Relic relic = new Relic();
		Document doc = Jsoup.connect(url).get();
		
		String title = doc.select("header > h1").text();
		String name = title.split(" \\(")[0];
		String desc = doc.select(".entry-content").text();
		String location, type;
		
		if (title.contains("La Khê")) {
			location = "Hà Đông";
		} else if (title.split(" \\(").length > 1) {
			location = title.split(" \\(")[1].replace(")", "");
		} else {
			location = "Hà Nội";
		}
		
		if (name.contains("Đình")) {
			type = "Đình";
		} else if (name.contains("Chùa")) {
			type = "Chùa";
		} else if (name.contains("Đền")) {
			type = "Đền";
		} else if (name.contains("Miếu")) {
			type = "Miếu";
		} else {
			type = "Nghè";
		}

		relic.setName(name);
		relic.setType(type);
		relic.setLocation(location);
		relic.setDesc(desc);
		
		// relic.print();
		
		return relic;
	}
	
	
}

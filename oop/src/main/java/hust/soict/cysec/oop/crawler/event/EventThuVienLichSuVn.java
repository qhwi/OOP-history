package hust.soict.cysec.oop.crawler.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.TimeUtility;
import hust.soict.cysec.oop.crawler.generic.LeafCrawler;
import hust.soict.cysec.oop.model.HistoricalEvent;

public class EventThuVienLichSuVn extends LeafCrawler<HistoricalEvent> {
	public EventThuVienLichSuVn() {
		super(Constants.JSON_EVENT);
	}
	
	@Override
	public List<HistoricalEvent> crawl() throws IOException {
		// GET ARTICLE URLs FROM MAIN PAGE
		List<HistoricalEvent> events = new LinkedList<>();
		
		String main_page = "https://thuvienlichsu.vn/su-kien?page=";
		List<String> urls = new ArrayList<String>();
		Document doc = null;
		
		for (int i = 1; i <= 20; i++) {
			doc = Jsoup.connect(main_page + i).get();
			Elements articles = doc.select("a[href*=/su-kien/]");
			String article_url = null;
			for (Element article : articles) {
				article_url = article.absUrl("href");
				if (!urls.contains(article_url) && !article_url.contains("https://thuvienlichsu.com/su-kien?page=")) {
					urls.add(article_url);
				}
			}
		}
		
		// CRAWL EVENT DATA
		for (String url : urls) {
			HistoricalEvent historicalEvent = crawlOnePage(url);
			events.add(historicalEvent);
		}
		
		return events;
	}
	
	public HistoricalEvent crawlOnePage(String url) throws IOException {
		System.out.println("[CRAWL EVENT] " + url);
		
		HistoricalEvent event = new HistoricalEvent();
		Document doc = Jsoup.connect(url).get();

		// Event Name, startTime, endTime
		Element title = doc.selectFirst("div.divide-line > h3");
		ArrayList<String> eventInfo = TimeUtility.extractYearRangeFromTitle(title.text());
		
		String name = eventInfo.get(0);
		event.setName(name);
		
		String start = eventInfo.get(1);
		event.setStartTime(start);
		
		String end = eventInfo.get(2);
		event.setEndTime(end);
		
		// Event Desc
		String desc = doc.select("div.card-body").get(1).text();
		event.setDesc(desc);
		
		// Event Locations
		Elements locations = doc.select("h3.card-title");
		for (Element location : locations) {
			event.addLocation(location.text());
		}

		// Event Related Figures
		Elements figures = doc.select("h4.card-title");
		for (Element figure : figures) {
			event.getFigure().add(figure.text());
		}
		
		return event;
	}
}

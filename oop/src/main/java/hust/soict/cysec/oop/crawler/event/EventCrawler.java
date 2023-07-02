package hust.soict.cysec.oop.crawler.event;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import hust.soict.cysec.oop.crawler.utils.TimeUtility;
import com.google.gson.stream.JsonWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EventCrawler {
	public static void main(String[] args) throws IOException {
		// GET ARTICLE URLs FROM MAIN PAGE
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
		
		//GET DATA FROM URLs
		OutputStream out = new FileOutputStream("src\\main\\json\\Event.json");
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
		writer.setIndent("  ");
		writer.beginArray();
		for (String string : urls) {
			System.out.println("[CRAWL EVENT] " + string);
			writer.beginObject();
			try {
				doc = Jsoup.connect(string).get();
			} catch (SocketTimeoutException e) {
				System.out.println(string + " --> fail");
			}
			// Event Name, startTime, endTime
			Element event = doc.selectFirst("div.divide-line > h3");
			ArrayList<String> eventInfo = TimeUtility.extractYearRangeFromTitle(event.text());
			writer.name("name").value(eventInfo.get(0));
			writer.name("startYear").value(eventInfo.get(1));
			writer.name("endYear").value(eventInfo.get(2));

			// Event Desc
			Element eventContent = doc.select("div.card-body").get(1);
			writer.name("description").value(eventContent.text());

			// Event Locations
			Elements relatedLoc = doc.select("h3.card-title");
			writer.name("locations");
			writer.beginArray();
			for (Element e1 : relatedLoc)
				writer.value(e1.text());
			writer.endArray();

			// Event Related Figures
			Elements relatedFigure = doc.select("h4.card-title");
			writer.name("figures");
			writer.beginArray();
			for (Element e2 : relatedFigure)
				writer.value(e2.text());
			writer.endArray();

			writer.endObject();

		}
		writer.endArray();
		writer.close();
	}
}

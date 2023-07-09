package hust.soict.cysec.oop.crawler.dynasty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.LeafCrawler;
import hust.soict.cysec.oop.model.Dynasty;
import hust.soict.cysec.oop.model.King;

public class DynastyWiki extends LeafCrawler<Dynasty>{
	public DynastyWiki() {
		super(Constants.JSON_DYNASTY);
	}
	
	@Override
	public List<Dynasty> crawl() throws IOException {
		List<Dynasty> dynasties = new LinkedList<>();
		
		String mainUrl = "https://vi.wikipedia.org/wiki/L%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
		
		Document doc = Jsoup.connect(mainUrl).get();
		Element table = doc.selectFirst("table.toccolours");
		Elements rows = table.select("tr");
		
		// GET DYNASTY's URLs FROM MAIN PAGE
		List<String> urls = new ArrayList<String>();
		for (int i = 2; i < rows.size() - 4; i++) {
			Elements tmp = rows.get(i).select("a[href]");
			if (tmp != null)
				for (Element t : tmp) {
					String url = t.absUrl("href");
					if (!urls.contains(url)) {
						if (!t.text().contains("–") && true) {
							urls.add(url);
						}
					}
				}
		}
		
		// GET DYNASTY's INFO FROM URLs
		for (String url : urls) {
			Dynasty dynasty = crawlOnePage(url);
			dynasties.add(dynasty);
		}
		
		return dynasties;
	}
	
	public Dynasty crawlOnePage(String url) throws IOException {
		System.out.println("[CRAWL DYNASTY] " + url);
		Dynasty dynasty = new Dynasty();

		Document doc = Jsoup.connect(url).get();
		
		int result = 0;
		String[] obj = new String[4];
		
		String name = doc.selectFirst("span.mw-page-title-main").text();
		
		Elements texts = doc.select("table.infobox > tbody > tr > td");
     
		// GET NAME, TIME
		if (texts.size() > 3) {
			
			String time = "";
			if (texts.get(0).text().contains("–") || texts.get(0).text().contains("-"))
				time = texts.get(0).text();
			else if (texts.get(1).text().contains("–") || texts.get(1).text().contains("-"))
				time = texts.get(1).text();
			else if (texts.get(3).text().contains("–") || texts.get(3).text().contains("-"))
				time = texts.get(3).text();
			else if (texts.get(2).text().contains("–") || texts.get(2).text().contains("-"))
				time = texts.get(2).text();
			
			if (!time.equals("")) {
				obj[0] = name;
				time = time.replace("← ", "").replace("→", "").replace("–", "-");
				String[] arr = time.split("-");
				try {
					int foundcount = 0;
					for (int j = 0; j < arr.length && foundcount < 2; j++) {
						String s = arr[j];
						Matcher matcher = Pattern.compile("\\d+").matcher(s);
						int num = 0;
						if (matcher.find()) {
							num = Integer.parseInt(matcher.group());
							foundcount++;
						} else {
							continue;
						}
						if (s.contains("TCN") || s.toLowerCase().contains("trước công nguyên")) {
							obj[foundcount] = Integer.toString(num) + " TCN";
						} else {
							obj[foundcount] = Integer.toString(num);
						}
					}
				} catch (java.lang.NumberFormatException e) {
					// No start or end year found
				}          
				result += 3;
			}
		}
		
		//GET CAPITAL
		String capital = "";
		Elements rows = doc.select("table.infobox > tbody > tr");
		for (Element row : rows) {
			Element tmp = row.selectFirst("a[href]");
			if (tmp != null) {
				if (tmp.attr("title").equals("Thủ đô")) {
					capital = row.selectFirst("td").text();
					obj[3] = capital;
					result++;
					break;
				}
			}
		}
		
		//GET KINGS
		List<String> kings = new ArrayList<String>();
		Elements kingRows = doc.select("tr.mergedrow");
		for (int j = 2; j < kingRows.size(); j++) {
			if (kingRows.get(j).text().equals(""))
				break;
			else {
				String king_name = kingRows.get(j).text().replace("• ", "");
				if (king_name.contains("Giải thể"))
					continue;
				king_name = king_name.replaceAll("\\s*\\([^)]+\\)", "");
				kings.add(king_name);
			}
		}
		
		if(result > 3) {
			dynasty.setName(obj[0]);
			dynasty.setStartYear(obj[1]);
			dynasty.setEndYear(obj[2]);
			dynasty.setCapital(obj[3]);
			for (String kingName : kings) {
				King king = new King();
				king.setName(kingName);
				dynasty.addKing(king);
			}
			
		}
		
		return dynasty;
	}
}
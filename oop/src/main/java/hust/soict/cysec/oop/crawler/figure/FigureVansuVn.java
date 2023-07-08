package hust.soict.cysec.oop.crawler.figure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.crawler.LeafCrawler;
import hust.soict.cysec.oop.model.Dynasty;
import hust.soict.cysec.oop.model.Figure;

/*
 * Crawl 2391 figures
 */
public class FigureVansuVn extends LeafCrawler<Figure> {	
	public static String ALIAS_IDENTIFIER = "Tên khác";
	public static String DOB_IDENTIFIER = "Năm sinh";
	public static String HOMETOWN_IDENTIFIER = "Tỉnh thành";
	public static String DYNASTIES_IDENTIFIER = "Thời kì";
	
	public List<Figure> crawl() throws IOException{
		List<Figure> figures = new ArrayList<Figure>();
		String url = "https://vansu.vn/viet-nam/viet-nam-nhan-vat/";
		// figure: from 1 to 2391
		for(int i = 1; i <= 2391; i++) {
			figures.add(crawlOnePage(url + i));
		}
		
		return figures;	
	}
	
	public static Figure crawlOnePage(String url) throws IOException {
		System.out.println("Crawl: " + url);
		Document doc = Jsoup.connect(url).get();
		Figure figure = new Figure();
		
		String name = doc.getElementsByClass("active section").first().text();
		figure.setName(name);
		
		Elements trs = doc.select("table").first().select("tbody").first().getElementsByTag("tr");
		for (Element tr : trs) {
			Elements tds = tr.select("td");
			if(tds.size() != 1) {
				String header = tds.get(0).text();
				if(header.contains(ALIAS_IDENTIFIER)) {
					figure.setAlias(tds.get(1).text());
				}else if(header.contains(DOB_IDENTIFIER)) {
					String[] years = tds.get(1).text().split("-");
					figure.setBirth(years[0].trim());
					figure.setDeath(years[1].trim());
				}else if(header.contains(HOMETOWN_IDENTIFIER)) {
					String hometown = tds.get(1).text();
					figure.setHometown(hometown);
				}else if(header.contains(DYNASTIES_IDENTIFIER)) {
					Elements brs = tds.get(1).getElementsByTag("br");
					if(brs.size() <= 1) {
						String data = tds.get(1).text();
						// Example: - Nhà Nguyễn độc lập (1802-1883)
						int index = data.indexOf("-");
						int openIndex = data.indexOf("(");
						if (openIndex != -1) {
							String dynastyName = data.substring(index + 2, openIndex).trim();
							Dynasty dynasty = new Dynasty();
							dynasty.setName(dynastyName);
							figure.addDynasty(dynasty);
						} else {
							String dynastyName = data.substring(index + 1).trim();
							Dynasty dynasty = new Dynasty();
							dynasty.setName(dynastyName);
							figure.addDynasty(dynasty);
						}
					}else { // nhieu thoi ki
						String data = brs.get(1).html();

						String[] dynasties = data.split("<br>");
						for (String d : dynasties) {
							int openIndex = d.indexOf("(");
							int index = d.indexOf("- ");
							if (openIndex != -1) {
								String dynastyName = d.substring(index + 1, openIndex).trim();
								Dynasty dynasty = new Dynasty();
								dynasty.setName(dynastyName);
								figure.addDynasty(dynasty);
							} 
							else {
								String dynastyName = d.substring(index + 1).trim();
								Dynasty dynasty = new Dynasty();
								dynasty.setName(dynastyName);
								figure.addDynasty(dynasty);
							}
						}
					}
				}
			}else {
				Elements paragraphs = tds.get(0).getElementsByTag("p");
				StringBuffer note = new StringBuffer();
				for (Element p : paragraphs) {
					note = note.append(p.text());
				}
				figure.setNote(note.toString());
			}
		}
		
		return figure;
	}
}

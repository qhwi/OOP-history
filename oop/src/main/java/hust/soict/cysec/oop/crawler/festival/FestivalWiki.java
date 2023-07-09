package hust.soict.cysec.oop.crawler.festival;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.LeafCrawler;
import hust.soict.cysec.oop.model.Festival;

public class FestivalWiki extends LeafCrawler<Festival> {
	public FestivalWiki() {
		super(Constants.JSON_FESTIVAL);
	}
	
	@Override
	public List<Festival> crawl() throws IOException {
		List<Festival> festivals = new LinkedList<>();
		
		String url = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam";
		Document doc = Jsoup.connect(url).get();
		
		Element table = doc.select("table").get(1);
		Elements rows = table.select("tr");
		
		for (int i = 1; i < rows.size(); i++) {
			Festival festival = new Festival();
			
			Element row = rows.get(i);  
			
			String date = row.select("td").get(0).text();
			festival.setTime(date);
			
			String place = row.select("td").get(1).text();
			festival.setLocation(place);
			
			String name = row.select("td").get(2).text();
			festival.setName(name);
			
			String[] figureNames = row.select("td").get(4).text().replaceAll("\\s*\\([^)]+\\)", "").split(", ");
			for (String figureName : figureNames) {
//				Figure figure = new Figure();
//				figure.setName(figureName);
//
//				festival.addFigure(figure);
				festival.addFigure(figureName);
			}
			
			String description = "Không rõ";
			try {
				String hyperlink = row.select("td").get(2).select("a").attr("href");
				if (!hyperlink.isBlank()) {
					hyperlink = "https://vi.wikipedia.org" + hyperlink;
					description = parseDescription(hyperlink);
					festival.setDesc(description);
				}
			} catch (Exception e) {
				System.out.println("Cannot parse description");
				e.printStackTrace();
			}
			
			festivals.add(festival);
		}
		
		return festivals;
	}
		
	
	public static String parseDescription(String url) {
		try {
			System.out.println("[CRAWL FESTIVAL] " + url);
			Document document = Jsoup.connect(url).get();
			Elements checkEmpty = document.select("#ca-edit > a");
			if (checkEmpty.text().contains("Tạo mã nguồn")) {
				return "Không rõ";
			}
            Elements elements = document.select("#mw-content-text > div.mw-parser-output > p");
            Elements checkContent = document.select("#mw-content-text > div.mw-parser-output > p.mw-empty-elt");
            String data;
            if (checkContent.isEmpty()) {
            	data = elements.get(0).text().trim();
            } else {
            	data = elements.get(1).text().trim();
            }
            String clean[] = data.split("(\\[)(\\b([0-9]|[1-9][0-9]|100)\\b)(\\])");
            data = "";
            for (int i = 0; i < clean.length; i++) {
            	data += clean[i];
            }
            return data;
		} catch (Exception e) {
			e.printStackTrace();
			return "Không rõ";
		}
	}
}

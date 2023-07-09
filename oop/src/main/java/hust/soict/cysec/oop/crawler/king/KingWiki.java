package hust.soict.cysec.oop.crawler.king;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.LeafCrawler;
import hust.soict.cysec.oop.model.King;

public class KingWiki extends LeafCrawler<King> {
	public KingWiki() {
		super(Constants.JSON_KING);
	}
	
	@Override
	public List<King> crawl() throws IOException {
		List<King> kings = new ArrayList<>();
		
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		System.out.println("Crawl: " + url);
		Document doc = Jsoup.connect(url).get();
		
		Elements firstTable = doc.select("table.toccolours");
		Elements tables = doc.select("table");
		for (Element t : firstTable) {
			if (tables.contains(t)) {
				tables.remove(t);
			}
		}
		int lastTable = tables.size() - 1;
		tables.remove(lastTable);
		String strRegEx = "<sup[^?]*";
		String attrRegEx = "<a[^?]*";
		for (Element table : tables) {
			Elements rows = table.select("tr");
			for (Element row : rows) {
				Elements datas = row.getElementsByTag("td");
				if (datas.size() >= 10 && datas.size() < 18) {
					King king = new King();
					
					String name = datas.get(1).text();
					name = cleanData(name);
					king.setName(name);
					
//					Element attr = datas.get(1).select("a").first();
//					if (attr != null) {
//						String url = attr.absUrl("href");
//						System.out.println(url);
//						king.setPaperURL(url);
//					}

					// Miến hiệu
					String templateName = datas.get(2).text();
					templateName = cleanData(templateName);
					king.setTemplateName(templateName);
					if(templateName.equals("")) {
						continue;
					}

					// Thụy hiệu
					String posthumousName = datas.get(3).text();
					posthumousName = cleanData(posthumousName);
					king.setPosthumousName(posthumousName);

					// Niên hiệu
					String eraName = datas.get(4).text();
					eraName = cleanData(eraName);
					king.setEraName(eraName);
					
					// Tên húy
					String courtesyName = datas.get(5).text();
					courtesyName = cleanData(courtesyName);
					king.setCourtesyName(courtesyName);

					// Thế thứ
					String successionOrder = datas.get(6).text();
					successionOrder = cleanData(successionOrder);
					king.setSuccessionOrder(successionOrder);
					
					// Năm trị vì
					String startReignYear = datas.get(7).html().replaceAll(strRegEx, "");
					String endReignYear = datas.get(9).html().replaceAll(strRegEx, "");
					String reignYear = startReignYear + " - " + endReignYear;
					reignYear = reignYear.replaceAll(attrRegEx, "");
					reignYear = cleanData(reignYear);
					king.setReignYear(reignYear);

					kings.add(king);
				} else
					continue;
			}
		}
		
		return kings;
	}
	
	private static String cleanData(String sample) {
		String data = new String(sample);
		int index = data.indexOf("[");
		while (index != -1) {
			int close = data.indexOf("]");
			String tmp = data.substring(index, close + 1);
			data = data.replace(tmp, "");
			index = data.indexOf("[");
		}
		return data;
	}
	
	
}

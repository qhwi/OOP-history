package hust.soict.cysec.oop.crawler.figure;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.LeafCrawler;
import hust.soict.cysec.oop.model.Figure;

public class FigureDoanhnghiepvnVn extends LeafCrawler<Figure> {
	public FigureDoanhnghiepvnVn() {
		super(Constants.JSON_FIGURE);
	}
	
	@Override
	public List<Figure> crawl() throws IOException{
		List<Figure> figures = new LinkedList<>();
		
		String url = "https://doanhnghiepvn.vn/kham-pha/chan-dung-10-anh-hung-trong-khang-chien-chong-phap/20200130024940748";
		System.out.println("[CRAWL FIGURE] " + url);
		Document doc = Jsoup.connect(url).get();
		
		Element mainBody = doc.getElementById("abody");
		Elements paragraphs = mainBody.select("span:contains(sinh)");
		
		for (Element e : paragraphs) {
			Figure figure = new Figure();
			
			String data = e.text();

			int index = data.indexOf(".");
			String infor = data.substring(0, index);
			if (infor.contains("Bế Văn Đàn")) {
				infor = infor.replace("Bế Văn Đàn, dân tộc Tày, sinh năm 1931",
						"Bế Văn Đàn sinh năm 1931, dân tộc Tày");
				infor = infor.replace(" tại", ", quê ở");
			}

			int endName = infor.indexOf(" sinh");
			String name = infor.substring(0, endName);
			figure.setName(name);

			int startDOB = infor.indexOf("1");
			String birth = infor.substring(startDOB, startDOB + 4);
			figure.setBirth(birth);
			figure.setDeath("Chưa biết");

			int startHomeTown = infor.indexOf("ở");
			String hometown = infor.substring(startHomeTown).replace("ở ", "");
			figure.setHometown(hometown);

			String note = data.substring(index + 2);
			figure.setNote(note);
			
			figures.add(figure);
		}
		
		return figures;
	}
}

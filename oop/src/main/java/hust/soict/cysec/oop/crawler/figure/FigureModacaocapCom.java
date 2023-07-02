package hust.soict.cysec.oop.crawler.figure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.model.Figure;

public class FigureModacaocapCom {
	public static List<Figure> crawlAll() throws IOException {
		List<Figure> figures = new ArrayList<>();
		
		String url = "https://modacaocap.com/danh-sach-trang-nguyen-viet-nam/";
		System.out.println("Crawl: " + url);
		Document doc = Jsoup.connect(url).get();
		
		Element mainBody = doc.getElementsByClass("entry-content single-page").first();
		Element table = mainBody.getElementsByClass("wikitable sortable jquery-tablesorter").first();
		Element tableBody = table.select("tbody").first();
		Elements rows = tableBody.select("tr");
		
		for (Element row : rows) {
			Figure figure = new Figure();
			Elements data = row.select("td");
			
			String name = data.get(1).text();
			figure.setName(name);
			
			String dob = data.get(2).text();
			String birth = "";
			String death = "";
			if (dob.equals("")) {
				birth = "Chưa biết";
				death = "Chưa biết";
			} else {
				int index = dob.indexOf("-");
				birth = dob.substring(0, index);
				death = dob.substring(index + 1);
				death = death.trim();
				if (death.equals("?")) {
					death = death.replace("?", "Chưa biết");
				}
			}
			figure.setBirth(birth);
			figure.setDeath(death);

			String hometown = data.get(3).text();
			figure.setHometown(hometown);
			
//			String namDoTrangNguyen = data.get(4).text();
			
//			String vua = data.get(5).text();
			
			String note = data.get(6).text();
			figure.setNote(note);
			
			figures.add(figure);
		}
		
		return figures;
	}
	
	public static void main(String[] args) throws IOException {
		List<Figure> figures = crawlAll();
		
		for (Figure figure : figures) {
			figure.print();
		}
	}
}

package hust.soict.cysec.oop.crawler.figure;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.model.Figure;

public class FigureHochiminhticyGov {
	public static List<Figure> crawlAll() throws IOException{
		List<Figure> figures = new LinkedList<>();
		
		String url = "http://www.quan8.hochiminhcity.gov.vn/dantaphaibietsuta/lists/posts/post.aspx?Source=/dantaphaibietsuta&Category=Nh%C3%A2n+v%E1%BA%ADt+l%E1%BB%8Bch+s%E1%BB%AD+t%E1%BB%AB+th%E1%BA%BF+k%E1%BB%B7+X+%C4%91%E1%BA%BFn+XV&ItemID=83&Mode=1";
		System.out.println("Crawl: " + url);
		Document doc = Jsoup.connect(url).get();
		
		Elements figureParagraph = doc.select("#part1 table div.ExternalClass6BD0A3317E7F4013AACBBE0FE404A173 p:has(b)");
		
		for (Element p : figureParagraph) {
			Figure figure = new Figure();
			Elements header = p.getElementsByTag("b");
			String name = header.text();
			if (!name.equals("")) {
				int index = name.indexOf(" ");
				name = name.substring(index);
				
				if (name.contains("(")) {
					index = name.indexOf("(");
					String dob = name.substring(index);
					name = name.substring(0, index);
					figure.setName(name);
					
					dob = dob.replace("(", "").replace(")", "");
					index = dob.indexOf("-");
					String birth = dob.substring(0, index);
					String death = dob.substring(index + 1);
					figure.setBirth(birth);
					figure.setDeath(death);
					
					figures.add(figure);
				} else {
					figure.setName(name);
					figures.add(figure);
				}
			}
		}
		
		return figures;
	}
}

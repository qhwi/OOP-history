package hust.soict.cysec.oop.crawler;

import java.io.IOException;

import hust.soict.cysec.oop.crawler.figure.FigureFinal;
import hust.soict.cysec.oop.crawler.king.KingFinal;
import hust.soict.cysec.oop.crawler.relic.RelicFinal;
import hust.soict.cysec.oop.model.Figure;
import hust.soict.cysec.oop.model.King;
import hust.soict.cysec.oop.model.Relic;

public class MainCrawler {

	public static void main(String[] args) {
		NodeCrawler<Figure> figureCrawler = new FigureFinal();
		
		NodeCrawler<King> kingCrawler = new KingFinal();
		
		NodeCrawler<Relic> relicCrawler = new RelicFinal();
		
		try {
			figureCrawler.envokeAllCrawlers();
			kingCrawler.envokeAllCrawlers();
			relicCrawler.envokeAllCrawlers();

			System.out.println("Figure size: " + figureCrawler.getCrawledList().size());
			System.out.println("King size: " + kingCrawler.getCrawledList().size());
			System.out.println("Relic size: " + relicCrawler.getCrawledList().size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

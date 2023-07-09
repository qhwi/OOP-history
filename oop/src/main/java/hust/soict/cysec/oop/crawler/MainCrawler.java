package hust.soict.cysec.oop.crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.crawler.dynasty.DynastyFinal;
import hust.soict.cysec.oop.crawler.event.EventFinal;
import hust.soict.cysec.oop.crawler.festival.FestivalFinal;
import hust.soict.cysec.oop.crawler.figure.FigureFinal;
import hust.soict.cysec.oop.crawler.generic.NodeCrawler;
import hust.soict.cysec.oop.crawler.king.KingFinal;
import hust.soict.cysec.oop.crawler.relic.RelicFinal;

public class MainCrawler {
	private List<NodeCrawler<?>> listCrawlers;
	
	public MainCrawler() {
		listCrawlers = new LinkedList<>();
		
		listCrawlers.add(new FigureFinal());
		listCrawlers.add(new KingFinal());
		listCrawlers.add(new RelicFinal());
		listCrawlers.add(new FestivalFinal());
		listCrawlers.add(new EventFinal());
		listCrawlers.add(new DynastyFinal());
	}
	
	public void envokeAllCrawlers() throws IOException {
		for (NodeCrawler<?> nodeCrawler : listCrawlers) {
			nodeCrawler.envokeAllCrawlers();
		}
	}
	
	public void writeToJson() throws IOException {
		for (NodeCrawler<?> nodeCrawler : listCrawlers) {
			JSONUtility.writeToJSON(nodeCrawler.JSON_URL, nodeCrawler.getCrawledList());
		}
	}

	public static void main(String[] args) {
		MainCrawler mainCrawler = new MainCrawler();
		
		try {
			mainCrawler.envokeAllCrawlers();
			
			mainCrawler.writeToJson();
		} catch (IOException e) {
			System.out.println("Error while running crawlers");
		}
		
	}
}

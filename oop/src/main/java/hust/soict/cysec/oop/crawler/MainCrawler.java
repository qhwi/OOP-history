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
	
	public List<NodeCrawler<?>> getListCrawlers(){
		return this.listCrawlers;
	}
	
	// Execute all crawler
	public void envokeAllCrawlers() throws IOException {
		for (NodeCrawler<?> nodeCrawler : listCrawlers) {
			envokeCrawler(nodeCrawler);
		}
	}
	
	// Execute only one crawlers
	public void envokeCrawler(NodeCrawler<?> nodeCrawler) throws IOException {
		nodeCrawler.envokeAllCrawlers();
		writeToJson(nodeCrawler);
	}
	
	// Write all objects
	public void writeAllToJson() throws IOException {
		for (NodeCrawler<?> nodeCrawler : listCrawlers) {
			writeToJson(nodeCrawler);
		}
	}
	
	// Write only one object
	public void writeToJson(NodeCrawler<?> nodeCrawler) throws IOException {
		JSONUtility.writeToJSON(nodeCrawler.JSON_URL, nodeCrawler.getCrawledList());
	}
}

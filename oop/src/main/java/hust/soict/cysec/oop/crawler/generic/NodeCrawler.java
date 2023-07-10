package hust.soict.cysec.oop.crawler.generic;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class NodeCrawler<T> extends Crawler<T> {
	private List<T> crawledList;
	private List<LeafCrawler<T>> crawlers;
	
	public NodeCrawler(String jsonURL) {
		super(jsonURL);
		
		this.crawlers = new LinkedList<>();
		this.crawledList = new LinkedList<>();
	}
	
	public List<T> getCrawledList(){
		return this.crawledList;
	}
	
	public void envokeAllCrawlers() throws IOException {
		for (LeafCrawler<T> crawler : crawlers) {
			List<T> objListFromLeaf = crawler.crawl();
			crawledList.addAll(objListFromLeaf);
		}
	}
	
	public void registerNewCrawler(LeafCrawler<T> crawler) {
		crawlers.add(crawler);
	}
}

package hust.soict.cysec.oop.crawler.generic;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class NodeCrawler<T> extends Crawler<T> {
	private List<Crawler<T>> crawlers;
	protected List<T> objList;
	
	public NodeCrawler(String jsonURL) {
		super(jsonURL);
		
		this.crawlers = new LinkedList<>();
		this.objList = new LinkedList<>();
	}
	
	public List<T> getCrawledList(){
		return this.objList;
	}
	
	public void envokeAllCrawlers() throws IOException {
		for (Crawler<T> crawler : crawlers) {
			if(crawler instanceof NodeCrawler) {
				((NodeCrawler<T>) crawler).envokeAllCrawlers();
			}else if(crawler instanceof LeafCrawler) {
				List<T> objListFromLeaf = ((LeafCrawler<T>) crawler).crawl();
				this.objList.addAll(objListFromLeaf);
			}
		}
	}
	
	public void registerNewCrawler(Crawler<T> crawler) {
		crawlers.add(crawler);
	}
}

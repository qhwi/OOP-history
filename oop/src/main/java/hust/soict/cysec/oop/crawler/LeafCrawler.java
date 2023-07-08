package hust.soict.cysec.oop.crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class LeafCrawler<T> extends Crawler<T> {
	protected List<T> objList;
	
	public LeafCrawler(){
		this.objList = new LinkedList<>();
	}
	
	public abstract List<T> crawl() throws IOException;
}

package hust.soict.cysec.oop.crawler.generic;

import java.io.IOException;
import java.util.List;

public abstract class LeafCrawler<T> extends Crawler<T> {
	public LeafCrawler(String jsonURL) {
		super(jsonURL);
	}
	
	public abstract List<T> crawl() throws IOException;
}

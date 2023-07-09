package hust.soict.cysec.oop.crawler.dynasty;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.NodeCrawler;
import hust.soict.cysec.oop.model.Dynasty;

public class DynastyFinal extends NodeCrawler<Dynasty> {
	public DynastyFinal() {
		super(Constants.JSON_DYNASTY);
		
		registerNewCrawler(new DynastyWiki());
	}
}

package hust.soict.cysec.oop.crawler.king;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.NodeCrawler;
import hust.soict.cysec.oop.model.King;

public class KingFinal extends NodeCrawler<King> {
	
	public KingFinal() {
		super(Constants.JSON_KING);
		
		registerNewCrawler(new KingWiki());
	}
}

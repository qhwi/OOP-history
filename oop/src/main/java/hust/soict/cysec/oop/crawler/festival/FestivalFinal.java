package hust.soict.cysec.oop.crawler.festival;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.NodeCrawler;
import hust.soict.cysec.oop.model.Festival;

public class FestivalFinal extends NodeCrawler<Festival> {
	public FestivalFinal() {
		super(Constants.JSON_FESTIVAL);
		
		registerNewCrawler(new FestivalWiki());
	}
}

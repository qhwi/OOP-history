package hust.soict.cysec.oop.crawler.relic;

import hust.soict.cysec.oop.crawler.NodeCrawler;
import hust.soict.cysec.oop.model.Relic;

public class RelicFinal extends NodeCrawler<Relic> {
	public static final String JSON_RELIC_PATH = "src\\main\\json\\Relic.json";
	
	public RelicFinal() {
		registerNewCrawler(new RelicDiTichVn());
		registerNewCrawler(new RelicDtlsvhxhCom());
	}
}

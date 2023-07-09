package hust.soict.cysec.oop.crawler.relic;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.NodeCrawler;
import hust.soict.cysec.oop.model.Relic;

public class RelicFinal extends NodeCrawler<Relic> {
	public static final String JSON_RELIC_PATH = "src\\main\\json\\Relic.json";
	
	public RelicFinal() {
		super(Constants.JSON_RELIC);
		
		registerNewCrawler(new RelicDiTichVn());
		registerNewCrawler(new RelicDtlsvhxhCom());
	}
}

package hust.soict.cysec.oop.crawler.event;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.NodeCrawler;
import hust.soict.cysec.oop.model.HistoricalEvent;

public class EventFinal extends NodeCrawler<HistoricalEvent> {
	public EventFinal() {
		super(Constants.JSON_EVENT);
		
		registerNewCrawler(new EventThuVienLichSuVn());
	}
}

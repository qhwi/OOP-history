package hust.soict.cysec.oop.filter.event;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.filter.generic.NodeFilter;
import hust.soict.cysec.oop.model.HistoricalEvent;

public class EventFilterFinal extends NodeFilter<HistoricalEvent> {
	public EventFilterFinal() {
		super(Constants.JSON_EVENT);
		
		// register
	}
}

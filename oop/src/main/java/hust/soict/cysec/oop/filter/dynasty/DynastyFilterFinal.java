package hust.soict.cysec.oop.filter.dynasty;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.filter.generic.NodeFilter;
import hust.soict.cysec.oop.model.HistoricalEvent;

public class DynastyFilterFinal extends NodeFilter<HistoricalEvent> {
	public DynastyFilterFinal() {
		super(Constants.JSON_DYNASTY);
		
		// register
	}
}

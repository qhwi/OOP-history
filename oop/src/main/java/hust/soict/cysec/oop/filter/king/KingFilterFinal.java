package hust.soict.cysec.oop.filter.king;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.filter.generic.NodeFilter;
import hust.soict.cysec.oop.model.King;

public class KingFilterFinal extends NodeFilter<King> {

	public KingFilterFinal() {
		super(Constants.JSON_KING);
		
		registerNewFilter(new KingFilterDuplicateName());
	}
	
}

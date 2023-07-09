package hust.soict.cysec.oop.filter.relic;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.filter.generic.NodeFilter;
import hust.soict.cysec.oop.model.Relic;

public class RelicFilterFinal extends NodeFilter<Relic> {

	public RelicFilterFinal() {
		super(Constants.JSON_RELIC);
		
		registerNewFilter(new RelicFilterDuplicateName());
	}

}

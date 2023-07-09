package hust.soict.cysec.oop.filter.festival;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.filter.generic.NodeFilter;
import hust.soict.cysec.oop.model.Festival;

public class FestivalFilterFinal extends NodeFilter<Festival> {

	public FestivalFilterFinal() {
		super(Constants.JSON_FESTIVAL);
		
		// register
	}
	
}

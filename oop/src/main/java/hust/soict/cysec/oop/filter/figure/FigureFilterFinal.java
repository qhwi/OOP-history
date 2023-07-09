package hust.soict.cysec.oop.filter.figure;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.filter.generic.NodeFilter;
import hust.soict.cysec.oop.model.Figure;

public class FigureFilterFinal extends NodeFilter<Figure> {

	public FigureFilterFinal() {
		super(Constants.JSON_FIGURE);
		
		registerNewFilter(new FigureFilterDuplicateName());
	}

}

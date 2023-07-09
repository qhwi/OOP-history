package hust.soict.cysec.oop.filter.figure;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.common.StringUtility;
import hust.soict.cysec.oop.filter.generic.LeafFilter;
import hust.soict.cysec.oop.model.Figure;

public class FigureFilterDuplicateName extends LeafFilter<Figure> {
	public FigureFilterDuplicateName() {
		super(Constants.JSON_FIGURE);
	}

	@Override
	public List<Figure> filter() throws IOException {
		System.out.println("[FIGURE FILTER] DUPLICATE NAME FILTER...");
		
		List<Figure> figuresFiltered = new LinkedList<>();
		List<Figure> figuresInJson = JSONUtility.readJson(JSON_URL, Figure.class);
		HashMap<String, Integer> names = new HashMap<String, Integer>();

		System.out.println("Size before: " + figuresInJson.size());
		
		for (Figure figure : figuresInJson) {
			if(!names.containsKey(figure.getName())) {
				// Add to list
				names.put(figure.getName(), figuresFiltered.size());
				figuresFiltered.add(figure);
			}else {
				// Check for attributes and add to missing fields
				Figure existedFigure = figuresFiltered.get(names.get(figure.getName()));
				
				if(StringUtility.isEmptyString(existedFigure.getAlias()) && !StringUtility.isEmptyString(figure.getAlias())) {
					existedFigure.setAlias(figure.getAlias());
				}
				if(StringUtility.isEmptyString(existedFigure.getBirth()) && !StringUtility.isEmptyString(figure.getBirth())) {
					existedFigure.setBirth(figure.getBirth());
				}
				if(StringUtility.isEmptyString(existedFigure.getDeath()) && !StringUtility.isEmptyString(figure.getDeath())) {
					existedFigure.setDeath(figure.getDeath());
				}
				if(StringUtility.isEmptyString(existedFigure.getHometown()) && !StringUtility.isEmptyString(figure.getHometown())) {
					existedFigure.setHometown(figure.getHometown());
				}
				if(StringUtility.isEmptyString(existedFigure.getNote()) && !StringUtility.isEmptyString(figure.getNote())) {
					existedFigure.setName(figure.getNote());
				}
				
				List<String> figureJsonDynasties = figure.getDynasties();
				if(figureJsonDynasties.size() != 0) {
					List<String> figureFilteredDynasties = existedFigure.getDynasties();

					for (String dynasty : figureJsonDynasties) {
						if(!figureFilteredDynasties.contains(dynasty)) {
							figureFilteredDynasties.add(dynasty);
						}
					}
				}
			}
		}
		
		System.out.println("Size after: " + figuresFiltered.size());
		
		return figuresFiltered;
	}

}

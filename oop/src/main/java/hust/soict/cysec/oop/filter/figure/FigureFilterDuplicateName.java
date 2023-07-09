package hust.soict.cysec.oop.filter.figure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.common.StringUtility;
import hust.soict.cysec.oop.filter.generic.LeafFilter;
import hust.soict.cysec.oop.model.Dynasty;
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
				
				if(StringUtility.checkEmptyString(existedFigure.getAlias()) && !StringUtility.checkEmptyString(figure.getAlias())) {
					existedFigure.setAlias(figure.getAlias());
				}
				if(StringUtility.checkEmptyString(existedFigure.getBirth()) && !StringUtility.checkEmptyString(figure.getBirth())) {
					existedFigure.setBirth(figure.getBirth());
				}
				if(StringUtility.checkEmptyString(existedFigure.getDeath()) && !StringUtility.checkEmptyString(figure.getDeath())) {
					existedFigure.setDeath(figure.getDeath());
				}
				if(StringUtility.checkEmptyString(existedFigure.getHometown()) && !StringUtility.checkEmptyString(figure.getHometown())) {
					existedFigure.setHometown(figure.getHometown());
				}
				if(StringUtility.checkEmptyString(existedFigure.getNote()) && !StringUtility.checkEmptyString(figure.getNote())) {
					existedFigure.setName(figure.getNote());
				}
				List<Dynasty> figureJsonDynasties = figure.getDynasties();
				if(figureJsonDynasties.size() != 0) {
					List<String> existedDynastyNames = new ArrayList<>();
					List<Dynasty> figureFilteredDynasties = existedFigure.getDynasties();
					
					for(Dynasty dynasty : figureFilteredDynasties) {
						existedDynastyNames.add(dynasty.getName());
					}
					
					for (Dynasty dynasty : figureJsonDynasties) {
						if(!existedDynastyNames.contains(dynasty.getName())) {
							existedDynastyNames.add(dynasty.getName());
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

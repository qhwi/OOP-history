package hust.soict.cysec.oop.filter.relic;

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
import hust.soict.cysec.oop.model.Relic;

public class RelicFilterDuplicateName extends LeafFilter<Relic> {

	public RelicFilterDuplicateName() {
		super(Constants.JSON_RELIC);
	}

	@Override
	public List<Relic> filter() throws IOException {
		System.out.println("[RELIC FILTER] DUPLICATE NAME FILTER...");
		
		List<Relic> relicsFiltered = new LinkedList<>();
		List<Relic> relicsInJson = JSONUtility.readJson(JSON_URL, Relic.class);
		HashMap<String, Integer> names = new HashMap<>();
		
		System.out.println("Size before: " + relicsInJson.size());
		
		for (Relic relic : relicsInJson) {
			if(!names.containsKey(relic.getName())) {
				names.put(relic.getName(), relicsFiltered.size());
				relicsFiltered.add(relic);
			}else {
				// Check for attributes and add to missing fields
				Relic existedRelic = relicsFiltered.get(names.get(relic.getName()));
				
				if(StringUtility.checkEmptyString(existedRelic.getLocation()) && !StringUtility.checkEmptyString(relic.getLocation())) {
					existedRelic.setLocation(relic.getLocation());
				}
				if(StringUtility.checkEmptyString(existedRelic.getType()) && !StringUtility.checkEmptyString(relic.getType())) {
					existedRelic.setType(relic.getType());
				}
				if(StringUtility.checkEmptyString(existedRelic.getRank()) && !StringUtility.checkEmptyString(relic.getRank())) {
					existedRelic.setRank(relic.getRank());
				}
				if(StringUtility.checkEmptyString(existedRelic.getDesc()) && !StringUtility.checkEmptyString(relic.getDesc())) {
					existedRelic.setDesc(relic.getDesc());
				}
				
				List<Dynasty> relicJsonDynasties = relic.getDynasties();
				if(relicJsonDynasties.size() != 0) {
					List<String> existedDynastyNames = new ArrayList<>();
					List<Dynasty> relicFilteredDynasties = existedRelic.getDynasties();
					
					for(Dynasty dynasty : relicFilteredDynasties) {
						existedDynastyNames.add(dynasty.getName());
					}
					
					for (Dynasty dynasty : relicFilteredDynasties) {
						if(!existedDynastyNames.contains(dynasty.getName())) {
							existedDynastyNames.add(dynasty.getName());
							relicFilteredDynasties.add(dynasty);
						}
					}
				}
				
				List<Figure> relicJsonFigures = relic.getRelatedFigures();
				if(relicJsonFigures.size() != 0) {
					List<String> existedFigureNames = new ArrayList<>();
					List<Figure> relicFilteredFigures = existedRelic.getRelatedFigures();
					
					for(Figure figure : relicFilteredFigures) {
						existedFigureNames.add(figure.getName());
					}
					
					for (Figure figure : relicFilteredFigures) {
						if(!existedFigureNames.contains(figure.getName())) {
							existedFigureNames.add(figure.getName());
							relicFilteredFigures.add(figure);
						}
					}
				}
			}
		}

		System.out.println("Size after: " + relicsFiltered.size());
		
		return relicsFiltered;
	}

}

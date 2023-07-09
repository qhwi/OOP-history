package hust.soict.cysec.oop.filter.relic;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.common.StringUtility;
import hust.soict.cysec.oop.filter.generic.LeafFilter;
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
				
				if(StringUtility.isEmptyString(existedRelic.getLocation()) && !StringUtility.isEmptyString(relic.getLocation())) {
					existedRelic.setLocation(relic.getLocation());
				}
				if(StringUtility.isEmptyString(existedRelic.getType()) && !StringUtility.isEmptyString(relic.getType())) {
					existedRelic.setType(relic.getType());
				}
				if(StringUtility.isEmptyString(existedRelic.getRank()) && !StringUtility.isEmptyString(relic.getRank())) {
					existedRelic.setRank(relic.getRank());
				}
				if(StringUtility.isEmptyString(existedRelic.getDesc()) && !StringUtility.isEmptyString(relic.getDesc())) {
					existedRelic.setDesc(relic.getDesc());
				}
				
				List<String> relicJsonDynasties = relic.getDynasties();
				if(relicJsonDynasties.size() != 0) {
					List<String> relicFilteredDynasties = existedRelic.getDynasties();
					
					for (String dynasty : relicFilteredDynasties) {
						if(!relicFilteredDynasties.contains(dynasty)) {
							relicFilteredDynasties.add(dynasty);
						}
					}
				}
				
				List<String> relicJsonFigures = relic.getRelatedFigures();
				if(relicJsonFigures.size() != 0) {
					List<String> relicFilteredFigures = existedRelic.getRelatedFigures();
					
					for (String figure : relicFilteredFigures) {
						if(!relicFilteredFigures.contains(figure)) {
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

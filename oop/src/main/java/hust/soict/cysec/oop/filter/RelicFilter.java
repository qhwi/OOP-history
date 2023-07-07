package hust.soict.cysec.oop.filter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import hust.soict.cysec.oop.crawler.relic.RelicFinal;
import hust.soict.cysec.oop.model.Dynasty;
import hust.soict.cysec.oop.model.Figure;
import hust.soict.cysec.oop.model.Relic;

public class RelicFilter {
	List<Relic> relicsFiltered;
	
	public RelicFilter() {
		this.relicsFiltered = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		RelicFilter relicFilter = new RelicFilter();
		
		relicFilter.filter();
		
		relicFilter.writeToJson();
	}
	
	public void filter() {
		List<Relic> relicsInJson = readData();
		HashMap<String, Integer> names = new HashMap<>();
		
		System.out.println("Size before: " + relicsInJson.size());
		
		for (Relic relic : relicsInJson) {
			if(!names.containsKey(relic.getName())) {
				names.put(relic.getName(), relicsFiltered.size());
				addRelic(relic);
			}else {
				// Check for attributes and add to missing fields
				Relic existedRelic = relicsFiltered.get(names.get(relic.getName()));
				
				if(checkEmptyString(existedRelic.getLocation()) && !checkEmptyString(relic.getLocation())) {
					existedRelic.setLocation(relic.getLocation());
				}
				if(checkEmptyString(existedRelic.getType()) && !checkEmptyString(relic.getType())) {
					existedRelic.setType(relic.getType());
				}
				if(checkEmptyString(existedRelic.getRank()) && !checkEmptyString(relic.getRank())) {
					existedRelic.setRank(relic.getRank());
				}
				if(checkEmptyString(existedRelic.getDesc()) && !checkEmptyString(relic.getDesc())) {
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
	}
	
	public List<Relic> readData() {
		Gson gson = new GsonBuilder().create();
		
		try {
			JsonReader reader = new JsonReader(new FileReader(RelicFinal.JSON_RELIC_PATH));

			List<Relic> relicsInJson = gson.fromJson(reader, new TypeToken<List<Relic>>() {}.getType());

			return relicsInJson;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot parse relic json file");
		}
		
		return null;
	}
	
	public void addRelic(Relic relic) {
		relicsFiltered.add(relic);
	}
	
	public boolean checkEmptyString(String s) {
		return s != null && !s.isEmpty() && !s.isBlank();
	}
	
	public void writeToJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(RelicFinal.JSON_RELIC_PATH));
			gson.toJson(relicsFiltered, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

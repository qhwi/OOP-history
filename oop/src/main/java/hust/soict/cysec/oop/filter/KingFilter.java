package hust.soict.cysec.oop.filter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import hust.soict.cysec.oop.crawler.historicalfigure.king.KingFinal;
import hust.soict.cysec.oop.model.King;

public class KingFilter {
	List<King> kingsFiltered;
	
	public KingFilter() {
		kingsFiltered = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		KingFilter kingFilter = new KingFilter();
		
		kingFilter.filter();
		
		kingFilter.writeToJson();
	}
	
	public void filter() {
		List<King> kingsInJson = readData();
		HashMap<String, Integer> names = new HashMap<String, Integer>();

		System.out.println("Size before: " + kingsInJson.size());
		
		for (King king : kingsInJson) {
			if(!names.containsKey(king.getName())) {
				// Add to list
				names.put(king.getName(), kingsFiltered.size());
				addKing(king);
			}else {
				
				// Check for attributes and add to missing fields
				King existedKing = kingsFiltered.get(names.get(king.getName()));
				
				if(checkEmptyString(existedKing.getBirth()) && !checkEmptyString(king.getBirth())) {
					existedKing.setBirth(king.getBirth());
				}
				if(checkEmptyString(existedKing.getDeath()) && !checkEmptyString(king.getDeath())) {
					existedKing.setDeath(king.getDeath());
				}
				if(checkEmptyString(existedKing.getTemplateName()) && !checkEmptyString(king.getTemplateName())) {
					existedKing.setTemplateName(king.getTemplateName());
				}
				if(checkEmptyString(existedKing.getPosthumousName()) && !checkEmptyString(king.getPosthumousName())) {
					existedKing.setPosthumousName(king.getPosthumousName());
				}
				if(checkEmptyString(existedKing.getEraName()) && !checkEmptyString(king.getEraName())) {
					existedKing.setEraName(king.getEraName());
				}
				if(checkEmptyString(existedKing.getCourtesyName()) && !checkEmptyString(king.getCourtesyName())) {
					existedKing.setCourtesyName(king.getCourtesyName());
				}
				if(checkEmptyString(existedKing.getSuccessionOrder()) && !checkEmptyString(king.getSuccessionOrder())) {
					existedKing.setSuccessionOrder(king.getSuccessionOrder());
				}
				if(checkEmptyString(existedKing.getReignYear()) && !checkEmptyString(king.getReignYear())) {
					existedKing.setReignYear(king.getReignYear());
				}
				if(checkEmptyString(existedKing.getPredecessor()) && !checkEmptyString(king.getPredecessor())) {
					existedKing.setPredecessor(king.getPredecessor());
				}
				if(checkEmptyString(existedKing.getSuccessor()) && !checkEmptyString(king.getSuccessor())) {
					existedKing.setSuccessor(king.getSuccessor());
				}
			}
		}
		
		System.out.println("Size after: " + kingsFiltered.size());
	}
	
	public List<King> readData() {
		Gson gson = new GsonBuilder().create();
		
		try {
			JsonReader reader = new JsonReader(new FileReader(KingFinal.JSON_KING_PATH));

			List<King> kingsInJson = gson.fromJson(reader, new TypeToken<List<King>>() {}.getType());

			return kingsInJson;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot parse figure json file");
		}
		return null;
	}
	
	public void writeToJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(KingFinal.JSON_KING_PATH));
			gson.toJson(kingsFiltered, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addKing(King king) {
		kingsFiltered.add(king);
	}
	
	public boolean checkEmptyString(String s) {
		return s != null && !s.isEmpty() && !s.isBlank();
	}
}

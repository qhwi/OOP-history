package hust.soict.cysec.oop.filter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.crawler.common.JSONUtility;
import hust.soict.cysec.oop.crawler.king.KingFinal;
import hust.soict.cysec.oop.model.King;

public class KingFilter {
	List<King> kingsFiltered;
	
	public KingFilter() {
		kingsFiltered = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		KingFilter kingFilter = new KingFilter();
		
		kingFilter.filter();
		
		JSONUtility.writeToJSON(KingFinal.JSON_KING_PATH, kingFilter.kingsFiltered);
	}
	
	public void filter() {
		List<King> kingsInJson = JSONUtility.readJson(KingFinal.JSON_KING_PATH, King.class);
		
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
	
	
	
	public void addKing(King king) {
		kingsFiltered.add(king);
	}
	
	public boolean checkEmptyString(String s) {
		return s != null && !s.isEmpty() && !s.isBlank();
	}
}

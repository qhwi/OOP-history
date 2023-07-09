package hust.soict.cysec.oop.model;

import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.StringUtility;

public class Dynasty {
	private String id;
	private String name;
	private String startYear;
	private String endYear;
	private List<String> kings;
	private String capital;
	private List<String> relatedId;
	
	public Dynasty() {
		this.kings = new LinkedList<>();
		this.relatedId = new LinkedList<>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(!StringUtility.isEmptyString(name))
			this.name = name;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		if(!StringUtility.isEmptyString(startYear))
			this.startYear = startYear;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		if(!StringUtility.isEmptyString(endYear))
			this.endYear = endYear;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		if(!StringUtility.isEmptyString(capital))
			this.capital = capital;
	}
	public List<String> getKings(){
		return this.kings;
	}
	
	public void addKing(String king) {
		if(!StringUtility.isEmptyString(king))
			this.kings.add(king);
	}
	
	public List<String> getRelatedId() {
		return this.relatedId;
	}
	
	public boolean checking(Object obj) {
		if (obj instanceof String) {
			String a = (String) obj;
			if (name != null && !name.isEmpty())
				return name.contains(a);
		}
		return false;
	}
	
	public void print() {
		System.out.println("----- " + this.name + " ------");
		System.out.println("Thời gian: " + this.startYear + " - " + this.endYear);
		System.out.println("Đời vua: ");
		for (String king : kings) {
			System.out.println("- " + king);
		}
		System.out.println("Thủ đô: " + capital);
	}
}

package hust.soict.cysec.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Dynasty {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String name;
	private String startYear;
	private String endYear;
	private List<String> kings = new ArrayList<>();
	private List<String> relatedId;
	private String capital;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public List<String> getKings(){
		return this.kings;
	}
	
	public List<String> getRelatedId(){
		return this.relatedId;
	}
//	public void addKing(King king) {
//		this.kings.add(king);
//	}
	public boolean checking(Object obj) {

		if (obj instanceof String) {
			String a = (String) obj;
			if (name != null && !name.isEmpty())
				return name.contains(a);
		}
		return false;
	}
	
}

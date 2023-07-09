package hust.soict.cysec.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Dynasty {
	private String name;
	private String startYear;
	private String endYear;
	private List<String> kings = new ArrayList<>();
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
//	public void addKing(King king) {
//		this.kings.add(king);
//	}
	
	
}

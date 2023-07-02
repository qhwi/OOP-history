package hust.soict.cysec.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Dynasty {
	private String name;
	private String startYear;
	private String endYear;
	private List<King> kings = new ArrayList<>();
	private String capital;
	private String founder;
	
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
	public String getFounder() {
		return founder;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	public List<King> getKings(){
		return this.kings;
	}
	public void addKing(King king) {
		kings.add(king);
	}
	
	
}
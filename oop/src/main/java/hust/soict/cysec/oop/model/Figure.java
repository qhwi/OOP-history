package hust.soict.cysec.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Figure extends HistoricalFigure {
	private String alias;
	private String hometown;
	private String note;
	private ArrayList<Dynasty> dynasties = new ArrayList<Dynasty>();
	private ArrayList<HistoricalEvent> eventJoined;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public void setDynasties(List<Dynasty> dynasties) {
		this.dynasties.addAll(dynasties);
	}
	
	public List<Dynasty> getDynasties(){
		return this.dynasties;
	}

	public void addDynasty(Dynasty dynasty) {
		this.dynasties.add(dynasty);
	}
	
	public void addEvent(HistoricalEvent event) {
		this.eventJoined.add(event);
	}
	
	public void print() {
		System.out.println("------" + getName() + "-------");
		System.out.println("Tên khác: " + getAlias());
		System.out.println("Năm sinh: " + getBirth() + " - " + getDeath());
		System.out.println("Tỉnh thành: " + getHometown());
		System.out.println("Triều đại: ");
		for (Dynasty dynasty : dynasties) {
			System.out.println("- " + dynasty.getName());
		}
		System.out.println("Ghi chú: " + getNote());
	}
}

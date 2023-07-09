package hust.soict.cysec.oop.model;

import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.StringUtility;

public class Figure extends HistoricalFigure {
	private String alias;
	private String hometown;
	private String note;
	private List<String> dynasties;
	private List<String> eventJoined;
	
	public Figure() {
		this.dynasties = new LinkedList<>();
		this.eventJoined = new LinkedList<>();
	}



	private List<String> relatedId;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getRelatedId() {
		return relatedId;
	}
	public void setRelatedId(List<String> relatedId) {
		this.relatedId = relatedId;
	}
	public List<String> getDynasties() {
		return dynasties;
	}
	public void setDynasties(List<String> dynasties) {
		this.dynasties = dynasties;
	}
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		if(!StringUtility.isEmptyString(alias))
			this.alias = alias;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		if(!StringUtility.isEmptyString(hometown))
			this.hometown = hometown;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		if(!StringUtility.isEmptyString(note))
			this.note = note;
	}

	public List<String> getEventJoined() {
		return eventJoined;
	}

	public void setEventJoined(List<String> eventJoined) {
		this.eventJoined = eventJoined;
	}

	public void addDynasty(String dynasty) {
		if(!StringUtility.isEmptyString(dynasty))
			this.dynasties.add(dynasty);
	}
	
	public void addEvent(String event) {
		if(!StringUtility.isEmptyString(event))
			this.eventJoined.add(event);
	}
	public void print() {
		System.out.println("------" + getName() + "-------");
		System.out.println("Tên khác: " + getAlias());
		System.out.println("Năm sinh: " + getBirth() + " - " + getDeath());
		System.out.println("Tỉnh thành: " + getHometown());
		System.out.println("Triều đại: ");
		for (String dynasty : dynasties) {
			System.out.println("- " + dynasty);
		}
		System.out.println("Ghi chú: " + getNote());
	}
}

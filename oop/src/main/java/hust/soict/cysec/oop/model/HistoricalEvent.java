package hust.soict.cysec.oop.model;

import java.util.LinkedList;
import java.util.List;

public class HistoricalEvent {
	private String name;
	private String startYear;
	private String endYear;
	private String desc;
	private List<String> locations;
	private List<String> figures;
	private List<String> relatedId;
	
	public HistoricalEvent() {
		this.locations = new LinkedList<>();
		this.figures = new LinkedList<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
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

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<String> getRelatedId() {
		return relatedId;
	}
	public void setRelatedId(List<String> relatedId) {
		this.relatedId = relatedId;
	}

	public List<String> getLocation() {
		return locations;
	}
	public void addLocation(String location) {
		this.locations.add(location);
	}
	public List<String> getFigure() {
		return figures;
	}
	public void addFigure(String figure) {
		this.figures.add(figure);
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
		System.out.println("Mô tả: " + this.desc);
		System.out.println("Địa điểm: ");
		for (String location : locations) {
			System.out.println("- " + location);
		}
		System.out.println("Nhân vật liên quan: ");
		for (String figure : figures) {
			System.out.println("- " + figure);
		}
	}
	
	
}

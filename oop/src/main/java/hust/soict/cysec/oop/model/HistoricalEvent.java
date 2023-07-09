package hust.soict.cysec.oop.model;

import java.util.List;

public class HistoricalEvent {
	private String name;
	private String startYear;
	private String endYear;
	private String description;
	private List<String> locations;
	private List<String> figures;
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
//	public void addFigure(Figure figure) {
//		this.figures.add(figure);
//	}
	
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

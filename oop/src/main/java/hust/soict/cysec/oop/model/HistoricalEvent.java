package hust.soict.cysec.oop.model;

import java.util.List;

public class HistoricalEvent {
	private String name;
	private String startYear;
	private String endYear;
	private String desc;
	private List<String> locations;
	private List<String> figures;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startYear;
	}
	public void setStartTime(String startTime) {
		this.startYear = startTime;
	}
	public String getEndTime() {
		return endYear;
	}
	public void setEndTime(String endTime) {
		this.endYear = endTime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	
	
}

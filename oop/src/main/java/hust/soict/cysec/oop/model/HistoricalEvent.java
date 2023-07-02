package hust.soict.cysec.oop.model;

import java.util.List;

public class HistoricalEvent {
	private String name;
	private String startYear;
	private String endYear;
	private String desc;
	private List<Relic> locations;
	private List<Figure> figures;
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
	public List<Relic> getLocation() {
		return locations;
	}
	public void addLocation(Relic location) {
		this.locations.add(location);
	}
	public List<Figure> getFigure() {
		return figures;
	}
	public void addFigure(Figure figure) {
		this.figures.add(figure);
	}
	
	
}

package hust.soict.cysec.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Festival {
	private String name;
	private String location;
	private String time;
	private List<String> activities = new ArrayList<>();
	private String desc;
	private List<Figure> figures = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<String> getActivities() {
		return activities;
	}
	public void setActivities(List<String> activities) {
		this.activities = activities;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Figure> getFigures() {
		return figures;
	}
	public void setFigures(List<Figure> figures) {
		this.figures = figures;
	}
	
	public void addActivity(String activity) {
		this.activities.add(activity);
	}
	
	public void addFigure(Figure figure) {
		this.figures.add(figure);
	}
	
	public void print() {
		System.out.println("Tên: " + this.getName());
		System.out.println("Địa điểm: " + this.getLocation());
		System.out.println("Thời gian: " + this.getTime());
		System.out.println("Mô tả: " + this.getDesc());
		System.out.println("Đối tượng suy tôn: ");
		for (Figure figure : figures) {
			System.out.println("- " + figure.getName());
		}
	}
}

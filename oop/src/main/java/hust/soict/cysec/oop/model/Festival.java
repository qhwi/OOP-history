package hust.soict.cysec.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Festival {
	private String name;
	private String location;
	private String time;
	private String desc;
	private List<Figure> figure = new ArrayList<>();
	
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Figure> getFigures() {
		return figure;
	}
	public void addFigure(Figure figure) {
		this.figure.add(figure);
	}
	
	public void print() {
		System.out.println("Tên: " + this.getName());
		System.out.println("Địa điểm: " + this.getLocation());
		System.out.println("Thời gian: " + this.getTime());
		System.out.println("Mô tả: " + this.getDesc());
		System.out.println("Nhân vật liên quan: ");
		for (Figure figure : figure) {
			System.out.println("- " + figure.getName());
		}
	}
}

package hust.soict.cysec.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Festival {
	private String name;
	private String location;
	private String date;
	private String description;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Figure> getFigures() {
		return figure;
	}
	public void addFigure(Figure figure) {
		this.figure.add(figure);
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
		System.out.println("Tên: " + this.getName());
		System.out.println("Địa điểm: " + this.getLocation());
		System.out.println("Thời gian: " + this.getDate());
		System.out.println("Mô tả: " + this.getDescription());
		System.out.println("Nhân vật liên quan: ");
		for (Figure figure : figure) {
			System.out.println("- " + figure.getName());
		}
	}
}

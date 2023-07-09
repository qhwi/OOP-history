package hust.soict.cysec.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Festival {
	private String name;
	private String location;
	private String time;
	private String desc;
	private List<String> figure;
	private List<String> relatedId;

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
//
//	public List<Figure> getFigures() {
//		return figure;
//	}
//	public void addFigure(Figure figure) {
//		this.figure.add(figure);
//	}
//	
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
		System.out.println("Thời gian: " + this.getTime());
		System.out.println("Mô tả: " + this.getDesc());
		System.out.println("Nhân vật liên quan: ");
//		for (Figure figure : figure) {
//			System.out.println("- " + figure.getName());
//		}
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
	public List<String> getRelatedId() {
		return relatedId;
	}
	public void setRelatedId(List<String> relatedId) {
		this.relatedId = relatedId;
	}
}

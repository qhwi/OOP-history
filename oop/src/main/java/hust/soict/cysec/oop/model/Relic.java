package hust.soict.cysec.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Relic {
	private String name;
	private String location;
	private String type;
	private String rank;
	private String desc;
	private List<Figure> relatedFigures;
	private List<Dynasty> dynasties;

	public Relic() {
		relatedFigures = new ArrayList<Figure>();
		dynasties = new ArrayList<Dynasty>();
	}
	
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
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Dynasty> getDynasties() {
		return dynasties;
	}
	public void setDynasties(List<Dynasty> dynasties) {
		this.dynasties = dynasties;
	}
	public List<Figure> getRelatedFigures(){
		return relatedFigures;
	}
	public void setRelatedFigures(List<Figure> relatedFigures) {
		this.relatedFigures = relatedFigures;
	}
	
	public void addFigure(Figure figure) {
		relatedFigures.add(figure);
	}
	
	public void addDynasty(Dynasty dynasty) {
		dynasties.add(dynasty);
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
		System.out.println("Loại hình di tích: " + this.getType());
		System.out.println("Xếp hạng: " + this.getRank());
		System.out.println("Đối tượng thờ: ");
		for (Figure figure : relatedFigures) {
			System.out.println("- " + figure.getName());
		}
	}
	
}

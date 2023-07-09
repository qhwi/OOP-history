package hust.soict.cysec.oop.model;

import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.StringUtility;

public class Relic {
	private String name;
	private String location;
	private String type;
	private String rank;
	private String desc;
	private List<String> relatedFigures;
	private List<String> dynasties;
	
	public Relic() {
		relatedFigures = new LinkedList<String>();
		dynasties = new LinkedList<String>();
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
		if(!StringUtility.isEmptyString(rank))
			this.rank = rank;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		if(!StringUtility.isEmptyString(desc))
			this.desc = desc;
	}
	public List<String> getDynasties() {
		return dynasties;
	}
	public void setDynasties(List<String> dynasties) {
		this.dynasties = dynasties;
	}
	public List<String> getRelatedFigures(){
		return relatedFigures;
	}
	public void setRelatedFigures(List<String> relatedFigures) {
		this.relatedFigures = relatedFigures;
	}
	
	public void addFigure(String figure) {
		if(!StringUtility.isEmptyString(figure))
			relatedFigures.add(figure);
	}
	
	public void addDynasty(String dynasty) {
		if(!StringUtility.isEmptyString(dynasty))
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
		for (String figure : relatedFigures) {
			System.out.println("- " + figure);
		}
	}
	
}

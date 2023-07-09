package hust.soict.cysec.oop.model;

import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.StringUtility;

public class Festival {
	private String name;
	private String location;
	private String time;
	private String desc;
	private List<String> figures;
	private List<String> relatedId;

	public Festival() {
		this.figures = new LinkedList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!StringUtility.isEmptyString(name))
			this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		if (!StringUtility.isEmptyString(location))
			this.location = location;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		if (!StringUtility.isEmptyString(time))
			this.time = time;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		if (!StringUtility.isEmptyString(desc))
			this.desc = desc;
	}

	public List<String> getFigures() {
		return figures;
	}

	public void addFigure(String figure) {
		if (!StringUtility.isEmptyString(figure))
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
		System.out.println("Tên: " + this.getName());
		System.out.println("Địa điểm: " + this.getLocation());
		System.out.println("Thời gian: " + this.getTime());
		System.out.println("Mô tả: " + this.getDesc());
		System.out.println("Nhân vật liên quan: ");
		for (String figure : figures) {
			System.out.println("- " + figure);
		}
	}

	public List<String> getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(List<String> relatedId) {
		this.relatedId = relatedId;
		for (String figure : figures) {
			System.out.println("- " + figure);
		}
	}
}

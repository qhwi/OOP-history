package hust.soict.cysec.oop.model;

import hust.soict.cysec.oop.common.StringUtility;

public abstract class HistoricalFigure {
	private String name;
	private String birth;
	private String death;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(!StringUtility.isEmptyString(name))
			this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		if(!StringUtility.isEmptyString(birth))
			this.birth = birth;
	}
	public String getDeath() {
		return death;
	}
	public void setDeath(String death) {
		if(!StringUtility.isEmptyString(death))
			this.death = death;
	}
	
	public boolean checking(Object obj) {

		if (obj instanceof String) {
			String a = (String) obj;
			if (name != null && !name.isEmpty())
				return name.contains(a);
		}
		return false;
	}
	
	
}

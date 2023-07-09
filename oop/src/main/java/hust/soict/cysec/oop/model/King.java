package hust.soict.cysec.oop.model;

import hust.soict.cysec.oop.common.StringUtility;

public class King extends HistoricalFigure {
	private String templateName; // Miến hiệu
	private String posthumousName; // Thụy hiệu
	private String eraName; // Niên hiệu
	private String courtesyName; // Tên húy
	private String successionOrder; // Thế thứ
	private String reignYear; // Năm trị vì

	private String predecessor; // Tiền nhiệm
	private String successor; // Kế nhiệm

	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		if(!StringUtility.isEmptyString(templateName))
			this.templateName = templateName;
	}
	public String getPosthumousName() {
		return posthumousName;
	}
	public void setPosthumousName(String posthumousName) {
		if(!StringUtility.isEmptyString(posthumousName))
			this.posthumousName = posthumousName;
	}
	public String getEraName() {
		return eraName;
	}
	public void setEraName(String eraName) {
		if(!StringUtility.isEmptyString(eraName))
			this.eraName = eraName;
	}
	public String getCourtesyName() {
		return courtesyName;
	}
	public void setCourtesyName(String courtesyName) {
		if(!StringUtility.isEmptyString(courtesyName))
			this.courtesyName = courtesyName;
	}
	public String getSuccessionOrder() {
		return successionOrder;
	}
	public void setSuccessionOrder(String successionOrder) {
		if(!StringUtility.isEmptyString(successionOrder))
			this.successionOrder = successionOrder;
	}
	public String getReignYear() {
		return reignYear;
	}
	public void setReignYear(String reignYear) {
		if(!StringUtility.isEmptyString(reignYear))
			this.reignYear = reignYear;
	}
	public String getPredecessor() {
		return predecessor;
	}
	public void setPredecessor(String predecessor) {
		if(!StringUtility.isEmptyString(predecessor))
			this.predecessor = predecessor;
	}
	public String getSuccessor() {
		return successor;
	}
	public void setSuccessor(String successor) {
		if(!StringUtility.isEmptyString(successor))
			this.successor = successor;
	}
	
	public void print() {
		System.out.println("----- " + this.getName() + " ------");
		System.out.println("Năm sinh: " + this.getBirth() + " - " + this.getDeath());
		System.out.println("Miến hiệu: " + this.getTemplateName());
		System.out.println("Thụy hiệu: " + this.getPosthumousName());
		System.out.println("Niên hiệu: " + this.getEraName());
		System.out.println("Tên húy: " + this.getCourtesyName());
		System.out.println("Thế thứ: " + this.getSuccessionOrder());
		System.out.println("Trị vì: " + this.getReignYear());
	}
	
}

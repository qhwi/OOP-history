package hust.soict.cysec.oop.model;

import java.util.List;

public class HistoricalEvent {
	private String name;
	private String startTime;
	private String endTime;
	private List<String> timelineOfEvent;
	private String desc;
	private Dynasty dynasty;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Dynasty getDynasty() {
		return dynasty;
	}
	public void setDynasty(Dynasty dynasty) {
		this.dynasty = dynasty;
	}
	public List<String> getTimelineOfEvent() {
		return timelineOfEvent;
	}
	public void setTimelineOfEvent(List<String> timeline) {
		this.timelineOfEvent = timeline;
	}
	
	public void addTimeline(String point) {
		this.timelineOfEvent.add(point);
	}
	
	
}

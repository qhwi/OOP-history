package hust.soict.cysec.oop.model;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

public class MainModel {
	private List<Dynasty> dynasties = new ArrayList<>();
	private List<Festival> festivals = new ArrayList<>();
	private List<HistoricalEvent> events = new ArrayList<>();
	private List<Figure> figures = new ArrayList<>();
	private List<King> kings = new ArrayList<>();
	private List<Relic> relics = new ArrayList<>();
	
	public MainModel() {
		try {
			Gson gson = new Gson();
			Dynasty dynastyTemp[] = null;
			FileReader readerDynasty = new FileReader("src/main/json/Dynasty.json");
			dynastyTemp = gson.fromJson(readerDynasty, Dynasty[].class);
			for (Dynasty dynasty : dynastyTemp) {
				dynasties.add(dynasty);
				System.out.println(dynasty);
			}
			
			Festival festivalTemp[] = null;
			FileReader readerFestival = new FileReader("src/main/json/Festival.json");
			festivalTemp = gson.fromJson(readerFestival, Festival[].class);
			for (Festival festival : festivalTemp) {
				festivals.add(festival);
			}
			
			HistoricalEvent eventTemp[] = null;
			FileReader readerEvent = new FileReader("src/main/json/Event.json");
			eventTemp = gson.fromJson(readerEvent, HistoricalEvent[].class);
			for (HistoricalEvent event : eventTemp) {
				events.add(event);
			}
			
			Figure figureTemp[] = null;
			FileReader readerFigure = new FileReader("src/main/json/Figures.json");
			figureTemp = gson.fromJson(readerFigure, Figure[].class);
			for (Figure figure : figureTemp) {
				figures.add(figure);
			}
			
			King kingTemp[] = null;
			FileReader readerKing = new FileReader("src/main/json/Kings.json");
			kingTemp = gson.fromJson(readerKing, King[].class);
			for (King king : kingTemp) {
				kings.add(king);
			}
			
			Relic relicTemp[] = null;
			FileReader readerRelic = new FileReader("src/main/json/Relic.json");
			relicTemp = gson.fromJson(readerRelic, Relic[].class);
			for (Relic relic : relicTemp) {
				relics.add(relic);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<Dynasty> getDynasties() {
		return dynasties;
	}
	
	public List<Festival> getFestivals() {
		return festivals;
	}
	
	public List<HistoricalEvent> getEvents() {
		return events;
	}
	
	public List<Figure> getFigures() {
		return figures;
	}
	
	public List<King> getKings() {
		return kings;
	}
	
	public List<Relic> getRelics() {
		return relics;
	}
}
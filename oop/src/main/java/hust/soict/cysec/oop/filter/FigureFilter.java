package hust.soict.cysec.oop.filter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import hust.soict.cysec.oop.crawler.figure.FigureFinal;
import hust.soict.cysec.oop.model.Dynasty;
import hust.soict.cysec.oop.model.Figure;

public class FigureFilter {
	List<Figure> figuresFiltered;
	
	public FigureFilter() {
		figuresFiltered = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		FigureFilter figureFilter = new FigureFilter();
		
		figureFilter.filter();
		
		figureFilter.writeToJson();
	}
	
	public void filter() {
		List<Figure> figuresInJson = readData();
		HashMap<String, Integer> names = new HashMap<String, Integer>();

		System.out.println("Size before: " + figuresInJson.size());
		
		for (Figure figure : figuresInJson) {
			if(!names.containsKey(figure.getName())) {
				// Add to list
				names.put(figure.getName(), figuresFiltered.size());
				addFigure(figure);
			}else {
				// Check for attributes and add to missing fields
				Figure existedFigure = figuresFiltered.get(names.get(figure.getName()));
				
				if(checkEmptyString(existedFigure.getAlias()) && !checkEmptyString(figure.getAlias())) {
					existedFigure.setAlias(figure.getAlias());
				}
				if(checkEmptyString(existedFigure.getBirth()) && !checkEmptyString(figure.getBirth())) {
					existedFigure.setBirth(figure.getBirth());
				}
				if(checkEmptyString(existedFigure.getDeath()) && !checkEmptyString(figure.getDeath())) {
					existedFigure.setDeath(figure.getDeath());
				}
				if(checkEmptyString(existedFigure.getHometown()) && !checkEmptyString(figure.getHometown())) {
					existedFigure.setHometown(figure.getHometown());
				}
				if(checkEmptyString(existedFigure.getNote()) && !checkEmptyString(figure.getNote())) {
					existedFigure.setName(figure.getNote());
				}
				List<Dynasty> figureJsonDynasties = figure.getDynasties();
				if(figureJsonDynasties.size() != 0) {
					List<String> existedDynastyNames = new ArrayList<>();
					List<Dynasty> figureFilteredDynasties = existedFigure.getDynasties();
					
					for(Dynasty dynasty : figureFilteredDynasties) {
						existedDynastyNames.add(dynasty.getName());
					}
					
					for (Dynasty dynasty : figureJsonDynasties) {
						if(!existedDynastyNames.contains(dynasty.getName())) {
							existedDynastyNames.add(dynasty.getName());
							figureFilteredDynasties.add(dynasty);
						}
					}
				}
			}
		}
		
		System.out.println("Size after: " + figuresFiltered.size());
	}
	
	public List<Figure> readData() {
		Gson gson = new GsonBuilder().create();
		
		try {
			JsonReader reader = new JsonReader(new FileReader(FigureFinal.JSON_FIGURE_PATH));

			List<Figure> figuresInJson = gson.fromJson(reader, new TypeToken<List<Figure>>() {}.getType());

			return figuresInJson;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot parse figure json file");
		}
		return null;
	}
	
	public void writeToJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(FigureFinal.JSON_FIGURE_PATH));
			gson.toJson(figuresFiltered, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addFigure(Figure figure) {
		figuresFiltered.add(figure);
	}
	
	public boolean checkEmptyString(String s) {
		return s != null && !s.isEmpty() && !s.isBlank();
	}
}

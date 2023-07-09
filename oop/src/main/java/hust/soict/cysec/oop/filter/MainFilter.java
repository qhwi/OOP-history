package hust.soict.cysec.oop.filter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.filter.figure.FigureFilterFinal;
import hust.soict.cysec.oop.filter.generic.NodeFilter;
import hust.soict.cysec.oop.filter.king.KingFilterFinal;
import hust.soict.cysec.oop.filter.relic.RelicFilterFinal;

public class MainFilter {
	private List<NodeFilter<?>> listFilters;
	
	public MainFilter() {
		listFilters = new LinkedList<>();
		
		listFilters.add(new KingFilterFinal());
		listFilters.add(new FigureFilterFinal());
		listFilters.add(new RelicFilterFinal());
	}
	
	public void envokeAllFilters() throws IOException {
		for (NodeFilter<?> nodeFilter : listFilters) {
			nodeFilter.envokeAllFilters();
		}
	}
	
	public void writeToJson() throws IOException {
		for (NodeFilter<?> nodeFilter : listFilters) {
			JSONUtility.writeToJSON(nodeFilter.JSON_URL, nodeFilter.getFilteredList());
		}
	}
	
	public static void main(String[] args) {
		MainFilter mainFilter = new MainFilter();
		
		try {
			mainFilter.envokeAllFilters();
			mainFilter.writeToJson();
		} catch (IOException e) {
			System.out.println("Error while running filter");
		}
		
	}
	
}

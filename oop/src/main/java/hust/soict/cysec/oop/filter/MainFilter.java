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
	
	public void envokeFilter(NodeFilter<?> nodeFilter) throws IOException {
		nodeFilter.envokeAllFilters();
		writeToJson(nodeFilter);
	}
	
	public void writeAllToJson() throws IOException {
		for (NodeFilter<?> nodeFilter : listFilters) {
			writeToJson(nodeFilter);
		}
	}
	
	public void writeToJson(NodeFilter<?> nodeFilter) throws IOException {
		JSONUtility.writeToJSON(nodeFilter.JSON_URL, nodeFilter.getFilteredList());
	}
	
}

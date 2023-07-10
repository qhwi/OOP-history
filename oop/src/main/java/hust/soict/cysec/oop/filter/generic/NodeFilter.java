package hust.soict.cysec.oop.filter.generic;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class NodeFilter<T> extends Filter<T> {
	private List<T> filteredList;
	private List<LeafFilter<T>> filters;
	
	public NodeFilter(String jsonURL) {
		super(jsonURL);
		
		filteredList = new LinkedList<>();
		filters = new LinkedList<>();
	}
	
	public List<T> getFilteredList(){
		return filteredList;
	}
	
	public void envokeAllFilters() throws IOException {
		for (LeafFilter<T> filter : filters) {
			List<T> objListFromLeaf =  filter.filter();
			filteredList.addAll(objListFromLeaf);
		}
	}
	
	public void registerNewFilter(LeafFilter<T> filter) {
		filters.add(filter);
	}
}

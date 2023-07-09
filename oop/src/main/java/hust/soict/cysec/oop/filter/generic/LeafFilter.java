package hust.soict.cysec.oop.filter.generic;

import java.io.IOException;
import java.util.List;

public abstract class LeafFilter<T> extends Filter<T> {
	public LeafFilter(String jsonURL) {
		super(jsonURL);
	}

	public abstract List<T> filter() throws IOException;
}

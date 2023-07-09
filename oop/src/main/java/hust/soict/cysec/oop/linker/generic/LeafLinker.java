package hust.soict.cysec.oop.linker.generic;

import java.io.IOException;
import java.util.List;

public abstract class LeafLinker<T> extends Linker<T> {

	public LeafLinker(String jsonURL) {
		super(jsonURL);
	}
	
	public abstract List<T> link() throws IOException;

}

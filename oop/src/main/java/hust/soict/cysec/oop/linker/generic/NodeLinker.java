package hust.soict.cysec.oop.linker.generic;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class NodeLinker<T> extends Linker<T> {
	private List<T> linkedList;
	private List<LeafLinker<T>> linkers;

	public NodeLinker(String jsonURL) {
		super(jsonURL);
		
		this.linkers = new LinkedList<>();
		this.linkedList = new LinkedList<>();
	}
	
	public List<T> getLinkedList(){
		return this.linkedList;
	}
	
	public abstract void assignId() throws IOException;
	
	public void envokeAllLinkers() throws IOException {
		for (LeafLinker<T> linker : linkers) {
			List<T> objListFromLeaf = linker.link();
			linkedList.addAll(objListFromLeaf);
		}
	}
	
	public void registerNewLinker(LeafLinker<T> linker) {
		linkers.add(linker);
	}
	
}

package hust.soict.cysec.oop.linker;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.linker.dynasty.DynastyFinalLinker;
import hust.soict.cysec.oop.linker.event.EventFinalLinker;
import hust.soict.cysec.oop.linker.festival.FestivalFinalLinker;
import hust.soict.cysec.oop.linker.figure.FigureFinalLinker;
import hust.soict.cysec.oop.linker.generic.NodeLinker;
import hust.soict.cysec.oop.linker.king.KingFinalLinker;

public class MainLinker {
	private List<NodeLinker<?>> listLinkers;
	
	public MainLinker() {
		listLinkers = new LinkedList<>();
		
		listLinkers.add(new FigureFinalLinker());
		listLinkers.add(new KingFinalLinker());
		listLinkers.add(new EventFinalLinker());
		listLinkers.add(new FestivalFinalLinker());
		listLinkers.add(new DynastyFinalLinker());
	}
	
	public List<NodeLinker<?>> getListLinkers(){
		return this.listLinkers;
	}
	
	// Assign id
	public void assignIdAllLinkers() throws IOException {
		for (NodeLinker<?> nodeLinker : listLinkers) {
			nodeLinker.assignId();
		}
	}
	
	// Execute all linkers
	public void envokeAllLinkers() throws IOException {
		for (NodeLinker<?> nodeLinker : listLinkers) {
			envokeLinker(nodeLinker);
		}
	}
	
	// Execute noly one linker
	public void envokeLinker(NodeLinker<?> nodeLinker) throws IOException {
		nodeLinker.envokeAllLinkers();
		writeToJson(nodeLinker);
	}
	
	// Write all objects
	public void writeAllToJson() throws IOException {
		for (NodeLinker<?> nodeLinker : listLinkers) {
			writeToJson(nodeLinker);
		}
	}
	
	// Write only one object
	public void writeToJson(NodeLinker<?> nodeLinker) throws IOException {
		JSONUtility.writeToJSON(nodeLinker.JSON_URL, nodeLinker.getLinkedList());
	}
	
	public static void main(String[] args) throws IOException {
		MainLinker mainLinker = new MainLinker();
		
		mainLinker.envokeAllLinkers();
		mainLinker.writeAllToJson();
	}
	
}

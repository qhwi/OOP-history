package hust.soict.cysec.oop.crawler.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hust.soict.cysec.oop.model.Relic;

public class RelicFinal {
	public static final String JSON_RELIC_PATH = "src\\main\\json\\Relic.json";

	private LinkedList<Relic> relics;
	
	public RelicFinal() {
		relics = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		RelicFinal relicFinal = new RelicFinal();
		relicFinal.combine();
	}
	
	public void combine() {
		try {
			List<Relic> relicDitichVn = RelicCrawlDiTichVn.crawAll();
			relics.addAll(relicDitichVn);
			
			List<Relic> relictDtlsvhxhCom = RelicCrawlDtlsvhxhCom.crawlAll();
			relics.addAll(relictDtlsvhxhCom);
			
			writeToJson();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void writeToJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(JSON_RELIC_PATH));
			gson.toJson(relics, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package hust.soict.cysec.oop.crawler.historicalfigure.king;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hust.soict.cysec.oop.model.King;

public class KingFinal {
	public static final String JSON_KING_PATH = "src\\main\\json\\Kings.json";

	private List<King> kings;
	
	public KingFinal() {
		this.kings = new LinkedList<>();
	}
	
	public void combine() {
		try {
			kings.addAll(KingWiki.crawlAll());
			
			writeToJson();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(JSON_KING_PATH));
			gson.toJson(kings, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

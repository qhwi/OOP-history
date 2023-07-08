package hust.soict.cysec.oop.crawler.figure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hust.soict.cysec.oop.crawler.NodeCrawler;
import hust.soict.cysec.oop.model.Figure;

public class FigureFinal extends NodeCrawler<Figure> {
	public static final String JSON_FIGURE_PATH = "src\\main\\json\\Figures.json";
	
	public FigureFinal() {
		registerNewCrawler(new FigureDoanhnghiepvnVn());
		registerNewCrawler(new FigureGiaoducNet());
		registerNewCrawler(new FigureModacaocapCom());
		registerNewCrawler(new FigureVansuVn());
	}
	
	public void writeToJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(JSON_FIGURE_PATH));
			gson.toJson(objList, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

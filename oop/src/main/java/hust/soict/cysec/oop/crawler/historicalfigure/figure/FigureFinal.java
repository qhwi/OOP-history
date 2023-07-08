package hust.soict.cysec.oop.crawler.historicalfigure.figure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hust.soict.cysec.oop.model.Figure;

public class FigureFinal {
	public static final String JSON_FIGURE_PATH = "src\\main\\json\\Figures.json";

	private List<Figure> figures;
	
	public FigureFinal() {
		this.figures = new LinkedList<>();
	}
	
	public void combine() {
		try {
			figures.addAll(FigureDoanhnghiepvnVn.crawlAll());
			
			figures.addAll(FigureGiaoducNet.crawlAll());
			
			figures.addAll(FigureModacaocapCom.crawlAll());
			
			figures.addAll(FigureVansuVn.crawlAll());
			
			writeToJson();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(JSON_FIGURE_PATH));
			gson.toJson(figures, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

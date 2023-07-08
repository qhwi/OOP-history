package hust.soict.cysec.oop.crawler.historicalfigure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hust.soict.cysec.oop.crawler.historicalfigure.figure.FigureFinal;
import hust.soict.cysec.oop.crawler.historicalfigure.king.KingFinal;
import hust.soict.cysec.oop.model.HistoricalFigure;

public class HistoricalFigureFinal {
	public static final String JSON_FIGURE_PATH = "src\\main\\json\\Figures.json";

	private List<HistoricalFigure> figures;
	
	public HistoricalFigureFinal() {
		this.figures = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		HistoricalFigureFinal historicalFigureFinal = new HistoricalFigureFinal();
		historicalFigureFinal.combine();
	}
	
	public void combine() {
		FigureFinal figureFinal = new FigureFinal();
		figureFinal.combine();
		
		KingFinal kingFinal = new KingFinal();
		kingFinal.combine();
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

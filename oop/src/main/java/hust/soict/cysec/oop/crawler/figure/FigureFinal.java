package hust.soict.cysec.oop.crawler.figure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hust.soict.cysec.oop.model.HistoricalFigure;

public class FigureFinal {
	public static final String JSON_FIGURE_PATH = "src\\main\\json\\Figures.json";

	private List<HistoricalFigure> figures;
	
	public FigureFinal() {
		this.figures = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		FigureFinal figureFinal = new FigureFinal();
		figureFinal.combine();
	}
	
	public void combine() {
		try {
			figures.addAll(FigureDoanhnghiepvnVn.crawlAll());
			
			figures.addAll(FigureGiaoducNet.crawlAll());
			
			figures.addAll(FigureModacaocapCom.crawlAll());
			
			figures.addAll(FigureVansuVn.crawlAll());
			
			figures.addAll(KingWiki.crawlAll());
			
			figures.addAll(FigureHochiminhticyGov.crawlAll());
			
			writeToJson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

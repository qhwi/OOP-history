package hust.soict.cysec.oop.crawler.figure;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.crawler.generic.NodeCrawler;
import hust.soict.cysec.oop.model.Figure;

public class FigureFinal extends NodeCrawler<Figure> {
	public FigureFinal() {
		super(Constants.JSON_FIGURE);
		
		registerNewCrawler(new FigureDoanhnghiepvnVn());
		registerNewCrawler(new FigureGiaoducNet());
		registerNewCrawler(new FigureModacaocapCom());
		registerNewCrawler(new FigureVansuVn());
	}
}

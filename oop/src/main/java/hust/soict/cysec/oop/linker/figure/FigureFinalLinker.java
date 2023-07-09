package hust.soict.cysec.oop.linker.figure;

import java.io.IOException;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.linker.generic.NodeLinker;
import hust.soict.cysec.oop.model.Figure;

public class FigureFinalLinker extends NodeLinker<Figure> {

	public FigureFinalLinker() {
		super(Constants.JSON_FIGURE);

		// regiser
		registerNewLinker(new FigureToDynastyLinker());
	}

	@Override
	public void assignId() throws IOException {
		List<Figure> figures = JSONUtility.readJson(Constants.JSON_FIGURE, Figure.class);
		for (int i = 0; i < figures.size(); i++) {
			Figure figure = figures.get(i);
			figure.setId("#F" + i);
		}
		JSONUtility.writeToJSON(Constants.JSON_FIGURE, figures);
	}

}

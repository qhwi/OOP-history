package hust.soict.cysec.oop.linker.festival;

import java.io.IOException;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.linker.generic.LeafLinker;
import hust.soict.cysec.oop.model.Festival;
import hust.soict.cysec.oop.model.Figure;

public class FestivalToFigureLinker extends LeafLinker<Festival> {
	
	public FestivalToFigureLinker() {
		super(Constants.JSON_FESTIVAL);
	}

	@Override
	public List<Festival> link() throws IOException {
		List<Festival> festivals = JSONUtility.readJson(Constants.JSON_FESTIVAL, Festival.class);
		List<Figure> figures = JSONUtility.readJson(Constants.JSON_FIGURE, Figure.class);
		
		for(Festival festival : festivals) {
			List<String> festivalFigureNames = festival.getFigures();
			for(String festivalFigureName : festivalFigureNames) {
				festivalFigureName = festivalFigureName.toLowerCase();
				for(Figure figure : figures) {
					String figureName = figure.getName().toLowerCase();
					String figureAlias = figure.getAlias() != null ? figure.getAlias().toLowerCase() : "@";
					
					if(festivalFigureName.contains(figureName) || festivalFigureName.contains(figureAlias)) {
						festival.getRelatedId().add(figure.getId());
						break;
					}
				}
			}
		}

		return festivals;
	}
}

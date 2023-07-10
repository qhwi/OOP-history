package hust.soict.cysec.oop.linker.king;

import java.io.IOException;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.linker.generic.LeafLinker;
import hust.soict.cysec.oop.model.Figure;
import hust.soict.cysec.oop.model.King;

public class KingToFigureLinker extends LeafLinker<King> {
	
	public KingToFigureLinker() {
		super(Constants.JSON_KING);
	}
	
	public List<King> link() throws IOException {
		System.out.println("[LINK KING] LINK TO FIGURE");
		
		List<King> kings = JSONUtility.readJson(Constants.JSON_KING, King.class);
		List<Figure> figures = JSONUtility.readJson(Constants.JSON_FIGURE, Figure.class);

		for (King king : kings) {
			String kingCourtesyName = king.getCourtesyName().toLowerCase();
			String kingName = king.getName().toLowerCase();
			for (Figure figure : figures) {
				String figureName = figure.getName().toLowerCase();
				String figureAlias = figure.getAlias() != null ? figure.getAlias().toLowerCase() : "@";

				if (kingName.contains(figureName) || kingName.contains(figureAlias)
						|| kingCourtesyName.contains(figureName) || kingCourtesyName.contains(figureAlias)) {
					king.getRelatedId().add(figure.getId());
					break;
				}
			}
		}
		
		return kings;
	}
}

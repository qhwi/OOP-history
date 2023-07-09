package hust.soict.cysec.oop.linker.figure;

import java.io.IOException;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.common.StringUtility;
import hust.soict.cysec.oop.linker.generic.LeafLinker;
import hust.soict.cysec.oop.model.Dynasty;
import hust.soict.cysec.oop.model.Figure;

public class FigureToDynastyLinker extends LeafLinker<Figure> {

	private String[][] DYNASTY_ALIAS = { { "Nhà Lê sơ", "Nhà Tiền Lê" }, { "Nhà Nguyễn", "Pháp đô hộ" },
			{ "Thời kỳ Bắc thuộc lần thứ hai", "Bắc thuộc lần 2" }, { "Nhà Mạc", "Nam - Bắc Triều" },
			{ "Thời kỳ Bắc thuộc lần thứ nhất", "Bắc thuộc lần 1" }, { "Nhà Hậu Trần", "Thuộc Minh" },
			{ "Nhà Lê sơ", "Triều Lê Sơ" }, { "Thời kỳ Bắc thuộc lần thứ ba", "Bắc thuộc lần lần 3" } };

	public FigureToDynastyLinker() {
		super(Constants.JSON_FIGURE);
	}
	
	@Override
	public List<Figure> link() throws IOException {
		List<Figure> figures = JSONUtility.readJson(Constants.JSON_FIGURE, Figure.class);
		List<Dynasty> dynasties = JSONUtility.readJson(Constants.JSON_DYNASTY, Dynasty.class);

		for (Figure figure : figures) {
			List<String> dynastyNames = figure.getDynasties(); // dynasties in figure
			for (String dynastyName : dynastyNames) {
				dynastyName = dynastyName.toLowerCase();

				// Check for empty value
				if (StringUtility.isEmptyString(dynastyName)) {
					continue;
				}

				// Replace
				for (String[] alias : DYNASTY_ALIAS) {
					if (dynastyName.equalsIgnoreCase(alias[1])) {
						dynastyName = alias[0].toLowerCase();
						break;
					}
				}

				// Link
				for (Dynasty dynasty : dynasties) {
					if (dynasty.getName().toLowerCase().contains(dynastyName)
							|| dynastyName.contains(dynasty.getName().toLowerCase())) {
						figure.getRelatedId().add(dynasty.getId());
					}
				}
			}
		}
		
		return figures;
	}
}
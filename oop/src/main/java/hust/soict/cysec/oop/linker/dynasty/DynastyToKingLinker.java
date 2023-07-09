package hust.soict.cysec.oop.linker.dynasty;

import java.io.IOException;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.linker.generic.LeafLinker;
import hust.soict.cysec.oop.model.Dynasty;
import hust.soict.cysec.oop.model.King;

public class DynastyToKingLinker extends LeafLinker<Dynasty> {
	
	public DynastyToKingLinker() {
		super(Constants.JSON_DYNASTY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Dynasty> link() throws IOException {
		System.out.println("[LINK DYNASTY] LINK TO KING");
		List<Dynasty> dynasties = JSONUtility.readJson(Constants.JSON_DYNASTY, Dynasty.class);
		List<King> kings = JSONUtility.readJson(Constants.JSON_KING, King.class);

		for (Dynasty dynasty : dynasties) {
			List<String> kingNames = dynasty.getKings();
			for (String kingName : kingNames) {
				kingName = kingName.toLowerCase();
				for (King king : kings) {
					if (kingName.contains(king.getName().toLowerCase())
							|| kingName.contains(king.getCourtesyName().toLowerCase())) {
						dynasty.getRelatedId().add(king.getId());
						break;
					}
				}
			}
		}
		
		return dynasties;
	}
}
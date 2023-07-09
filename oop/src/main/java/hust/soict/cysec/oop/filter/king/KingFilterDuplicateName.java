package hust.soict.cysec.oop.filter.king;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.common.StringUtility;
import hust.soict.cysec.oop.filter.generic.LeafFilter;
import hust.soict.cysec.oop.model.King;

public class KingFilterDuplicateName extends LeafFilter<King> {

	public KingFilterDuplicateName() {
		super(Constants.JSON_KING);
	}

	@Override
	public List<King> filter() throws IOException {
		System.out.println("[KING FILTER] DUPLICATE NAME FILTER...");
		
		List<King> kingsFiltered = new LinkedList<>();
		List<King> kingsInJson = JSONUtility.readJson(JSON_URL, King.class);

		HashMap<String, Integer> names = new HashMap<String, Integer>();

		System.out.println("Size before: " + kingsInJson.size());

		for (King king : kingsInJson) {
			if (!names.containsKey(king.getName())) {
				// Add to list
				names.put(king.getName(), kingsFiltered.size());
				kingsFiltered.add(king);
			} else {

				// Check for attributes and add to missing fields
				King existedKing = kingsFiltered.get(names.get(king.getName()));

				if (StringUtility.checkEmptyString(existedKing.getBirth()) && !StringUtility.checkEmptyString(king.getBirth())) {
					existedKing.setBirth(king.getBirth());
				}
				if (StringUtility.checkEmptyString(existedKing.getDeath()) && !StringUtility.checkEmptyString(king.getDeath())) {
					existedKing.setDeath(king.getDeath());
				}
				if (StringUtility.checkEmptyString(existedKing.getTemplateName()) && !StringUtility.checkEmptyString(king.getTemplateName())) {
					existedKing.setTemplateName(king.getTemplateName());
				}
				if (StringUtility.checkEmptyString(existedKing.getPosthumousName()) && !StringUtility.checkEmptyString(king.getPosthumousName())) {
					existedKing.setPosthumousName(king.getPosthumousName());
				}
				if (StringUtility.checkEmptyString(existedKing.getEraName()) && !StringUtility.checkEmptyString(king.getEraName())) {
					existedKing.setEraName(king.getEraName());
				}
				if (StringUtility.checkEmptyString(existedKing.getCourtesyName()) && !StringUtility.checkEmptyString(king.getCourtesyName())) {
					existedKing.setCourtesyName(king.getCourtesyName());
				}
				if (StringUtility.checkEmptyString(existedKing.getSuccessionOrder())
						&& !StringUtility.checkEmptyString(king.getSuccessionOrder())) {
					existedKing.setSuccessionOrder(king.getSuccessionOrder());
				}
				if (StringUtility.checkEmptyString(existedKing.getReignYear()) && !StringUtility.checkEmptyString(king.getReignYear())) {
					existedKing.setReignYear(king.getReignYear());
				}
				if (StringUtility.checkEmptyString(existedKing.getPredecessor()) && !StringUtility.checkEmptyString(king.getPredecessor())) {
					existedKing.setPredecessor(king.getPredecessor());
				}
				if (StringUtility.checkEmptyString(existedKing.getSuccessor()) && !StringUtility.checkEmptyString(king.getSuccessor())) {
					existedKing.setSuccessor(king.getSuccessor());
				}
			}
		}

		System.out.println("Size after: " + kingsFiltered.size());
		return kingsFiltered;
	}

}

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

				if (StringUtility.isEmptyString(existedKing.getBirth()) && !StringUtility.isEmptyString(king.getBirth())) {
					existedKing.setBirth(king.getBirth());
				}
				if (StringUtility.isEmptyString(existedKing.getDeath()) && !StringUtility.isEmptyString(king.getDeath())) {
					existedKing.setDeath(king.getDeath());
				}
				if (StringUtility.isEmptyString(existedKing.getTemplateName()) && !StringUtility.isEmptyString(king.getTemplateName())) {
					existedKing.setTemplateName(king.getTemplateName());
				}
				if (StringUtility.isEmptyString(existedKing.getPosthumousName()) && !StringUtility.isEmptyString(king.getPosthumousName())) {
					existedKing.setPosthumousName(king.getPosthumousName());
				}
				if (StringUtility.isEmptyString(existedKing.getEraName()) && !StringUtility.isEmptyString(king.getEraName())) {
					existedKing.setEraName(king.getEraName());
				}
				if (StringUtility.isEmptyString(existedKing.getCourtesyName()) && !StringUtility.isEmptyString(king.getCourtesyName())) {
					existedKing.setCourtesyName(king.getCourtesyName());
				}
				if (StringUtility.isEmptyString(existedKing.getSuccessionOrder())
						&& !StringUtility.isEmptyString(king.getSuccessionOrder())) {
					existedKing.setSuccessionOrder(king.getSuccessionOrder());
				}
				if (StringUtility.isEmptyString(existedKing.getReignYear()) && !StringUtility.isEmptyString(king.getReignYear())) {
					existedKing.setReignYear(king.getReignYear());
				}
				if (StringUtility.isEmptyString(existedKing.getPredecessor()) && !StringUtility.isEmptyString(king.getPredecessor())) {
					existedKing.setPredecessor(king.getPredecessor());
				}
				if (StringUtility.isEmptyString(existedKing.getSuccessor()) && !StringUtility.isEmptyString(king.getSuccessor())) {
					existedKing.setSuccessor(king.getSuccessor());
				}
			}
		}

		System.out.println("Size after: " + kingsFiltered.size());
		return kingsFiltered;
	}

}

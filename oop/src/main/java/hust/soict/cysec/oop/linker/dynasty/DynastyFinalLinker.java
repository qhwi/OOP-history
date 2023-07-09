package hust.soict.cysec.oop.linker.dynasty;

import java.io.IOException;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.linker.generic.NodeLinker;
import hust.soict.cysec.oop.model.Dynasty;

public class DynastyFinalLinker extends NodeLinker<Dynasty> {

	public DynastyFinalLinker() {
		super(Constants.JSON_DYNASTY);
		
		// register
		registerNewLinker(new DynastyToKingLinker());
	}

	@Override
	public void assignId() throws IOException {
		List<Dynasty> dynasties = JSONUtility.readJson(Constants.JSON_DYNASTY, Dynasty.class);
        for(int i = 0; i < dynasties.size(); i++){
        	Dynasty dynasty = dynasties.get(i);
        	dynasty.setId("#D" + i);
        }
        JSONUtility.writeToJSON(Constants.JSON_DYNASTY, dynasties);
	}

}

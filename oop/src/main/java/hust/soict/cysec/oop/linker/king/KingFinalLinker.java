package hust.soict.cysec.oop.linker.king;

import java.io.IOException;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.linker.generic.NodeLinker;
import hust.soict.cysec.oop.model.King;

public class KingFinalLinker extends NodeLinker<King> {

	public KingFinalLinker() {
		super(Constants.JSON_KING);
		
		// register
		registerNewLinker(new KingToFigureLinker());
	}

	@Override
	public void assignId() throws IOException {
		List<King> kings = JSONUtility.readJson(Constants.JSON_KING, King.class);
        for(int i = 0; i < kings.size(); i++){
        	King king = kings.get(i);
        	king.setId("#K" + i);
        }
        JSONUtility.writeToJSON(Constants.JSON_KING, kings);
	}

}

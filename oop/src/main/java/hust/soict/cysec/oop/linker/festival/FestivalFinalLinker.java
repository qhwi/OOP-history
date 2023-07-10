package hust.soict.cysec.oop.linker.festival;

import java.io.IOException;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.linker.generic.NodeLinker;
import hust.soict.cysec.oop.model.Festival;

public class FestivalFinalLinker extends NodeLinker<Festival> {

	public FestivalFinalLinker() {
		super(Constants.JSON_FESTIVAL);
		
		// register
		registerNewLinker(new FestivalToFigureLinker());
	}

	@Override
	public void assignId() throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}

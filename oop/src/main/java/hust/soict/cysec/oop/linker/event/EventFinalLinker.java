package hust.soict.cysec.oop.linker.event;

import java.io.IOException;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.linker.generic.NodeLinker;
import hust.soict.cysec.oop.model.HistoricalEvent;

public class EventFinalLinker extends NodeLinker<HistoricalEvent>{

	public EventFinalLinker() {
		super(Constants.JSON_EVENT);
		
		// register
		registerNewLinker(new EventToFigureLinker());
	}

	@Override
	public void assignId() throws IOException {
		// TODO Auto-generated method stub
		
	}

}

package hust.soict.cysec.oop.linker.event;

import java.io.IOException;
import java.util.List;

import hust.soict.cysec.oop.common.Constants;
import hust.soict.cysec.oop.common.JSONUtility;
import hust.soict.cysec.oop.linker.generic.LeafLinker;
import hust.soict.cysec.oop.model.Figure;
import hust.soict.cysec.oop.model.HistoricalEvent;

public class EventToFigureLinker extends LeafLinker<HistoricalEvent> {
	
	public EventToFigureLinker() {
		super(Constants.JSON_EVENT);
	}

	@Override
    public List<HistoricalEvent> link() throws IOException {
		System.out.println("[LINK EVENT] LINK TO FIGURE");
		
    	List<HistoricalEvent> events = JSONUtility.readJson(Constants.JSON_EVENT, HistoricalEvent.class);
    	List<Figure> figures = JSONUtility.readJson(Constants.JSON_FIGURE, Figure.class);
    	
    	for(HistoricalEvent event : events) {
    		List<String> eventFigureNames = event.getFigure();
    		for(String eventFigureName : eventFigureNames) {
    			eventFigureName = eventFigureName.toLowerCase();
    			for(Figure figure : figures) {
    				String figureName = figure.getName().toLowerCase();
    				String figureAlias = figure.getAlias() != null ? figure.getAlias().toLowerCase() : "@";
    				
    				if(eventFigureName.contains(figureName) || eventFigureName.contains(figureAlias)) {
    					event.getRelatedId().add(figure.getId());
    				}
    			}
    		}
    	}

    	return events;
    }
}

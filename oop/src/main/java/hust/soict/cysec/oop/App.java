package hust.soict.cysec.oop;
import java.io.IOException;

import hust.soict.cysec.oop.crawler.dynasty.DynastyCrawler;
import hust.soict.cysec.oop.crawler.event.EventCrawler;
import hust.soict.cysec.oop.crawler.festival.FestivalCrawler;
import hust.soict.cysec.oop.crawler.historicalfigure.HistoricalFigureFinal;
import hust.soict.cysec.oop.crawler.relic.RelicFinal;

public class App {
	public static void main( String[] args ) throws IOException {
    	System.out.println("\n>>\n>> Crawling Dynasties...\n>>\n");
    	DynastyCrawler.main(args);
    	System.out.println("\n>>\n>> Crawling Festivals...\n>>\n");
    	FestivalCrawler.main(args);
    	System.out.println("\n>>\n>> Crawling Events...\n>>\n");
    	EventCrawler.main(args);
		System.out.println("\n>>\n>> Crawling Relics...\n>>\n");
		RelicFinal.main(args);
		System.out.println("\n>>\n>> Crawling Figures...\n>>\n");
		HistoricalFigureFinal.main(args);
        System.out.println("\n>>DONE!");
    }
}

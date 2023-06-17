package hust.soict.cysec.oop;
import java.io.IOException;

import hust.soict.cysec.oop.crawler.*;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	System.out.println("Crawling Dynasties...");
    	DynastyCrawler.main(args);
    	System.out.println("Crawling Festivals...");
    	FestivalCrawler.main(args);
        System.out.println("DONE!");
    }
}

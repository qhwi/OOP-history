package hust.soict.cysec.oop;
import java.io.IOException;

import hust.soict.cysec.oop.crawler.DynastyCrawler;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	DynastyCrawler.main(args);
        System.out.println( "DONE!" );
    }
}

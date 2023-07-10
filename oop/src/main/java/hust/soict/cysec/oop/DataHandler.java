package hust.soict.cysec.oop;

import java.io.IOException;

import hust.soict.cysec.oop.crawler.MainCrawler;
import hust.soict.cysec.oop.filter.MainFilter;
import hust.soict.cysec.oop.linker.MainLinker;

public class DataHandler {
	public static void main(String[] args) {
		MainCrawler mainCrawler = new MainCrawler();
		MainFilter mainFilter = new MainFilter();
		MainLinker mainLinker = new MainLinker();
		
		try {
			mainCrawler.envokeAllCrawlers();
			
			mainFilter.envokeAllFilters();
			
			mainLinker.assignIdAllLinkers();
			
			mainLinker.envokeAllLinkers();
			
			System.out.println("Data is ready to use!");
		} catch (IOException e) {
			System.out.println("Error while handling data");
		}
	}
}

package hust.soict.cysec.oop.crawler.dynasty;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hust.soict.cysec.oop.crawler.utils.TimeUtility;
import hust.soict.cysec.oop.model.Dynasty;

public class DynastyWikiCrawler {
	private String url;
	private ArrayList<Dynasty> dynastyList;
	
	public DynastyWikiCrawler() {
		dynastyList = new ArrayList<Dynasty>();
		this.url = "https://vi.wikipedia.org/wiki/L%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
		
	}
	
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public ArrayList<Dynasty> getDynastyList() {
		return dynastyList;
	}

	public void setDynastyList(ArrayList<Dynasty> dynastyList) {
		this.dynastyList = dynastyList;
	}

	public Document getData() throws IOException {
		Document doc = Jsoup.connect(this.url).get();
		return doc;
	}
	
	public void extract(Document doc){
		Elements trs = doc.select("table.table.toccolours tr>td>table>tbody>tr");
		for (Element tr : trs) {
			Dynasty dynasty = new Dynasty();
			
			Element a = tr.selectFirst("b a");
			if(a == null) {
				continue;
			}
			dynasty.setName(a.text());
			
			Element font = tr.selectFirst("font");
			if(font != null) {
				ArrayList<String> yearArr = TimeUtility.extractNumbersFromString("(\\d+ \\w+ - \\d+ \\w+)", font.text());
				System.out.println(yearArr.toString());
				dynasty.setStartYear(yearArr.get(0));
				dynasty.setEndYear(yearArr.get(1));
			}
			dynastyList.add(dynasty);
		}
	}
}

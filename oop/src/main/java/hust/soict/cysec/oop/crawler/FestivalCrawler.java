package hust.soict.cysec.oop.crawler;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.stream.JsonWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FestivalCrawler {

	public static void main(String[] args) throws IOException {
	      String url = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam";
	      Element table = Jsoup.connect(url).get().select("table").get(1);
	      Elements rows = table.select("tr");
	      OutputStream out = new FileOutputStream("src\\\\main\\\\json\\\\Festival.json");
	      JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
	      writer.setIndent("  ");
	      writer.beginArray();
	      for (int i = 1; i < rows.size(); i++) {
	         Element row = rows.get(i);  
	            
	         String date = row.select("td").get(0).text();
	         String place = row.select("td").get(1).text();
	         String name = row.select("td").get(2).text();
	         String figure = row.select("td").get(4).text();
	         
	         String description = "Không rõ";
	         try {
	        	 String hyperlink = row.select("td").get(2).select("a").attr("href");
	        	 if (!hyperlink.isBlank()) {
	        		 hyperlink = "https://vi.wikipedia.org" + hyperlink;
	        		 description = parseDescription(hyperlink);
	        	 }
	         } catch (Exception e) {
	        	 e.printStackTrace();
	         }
	         
	         //WRITE
	         writer.beginObject();
	         writer.name("name").value(name);
	         writer.name("date").value((date.isBlank()) ? "Không rõ" : date);
	         writer.name("location").value((place.contains("Nhiều")) ? "Cả nước" : place);
	         writer.name("description").value(description);
	         writer.name("relatedFigure").value(figure);
	         writer.endObject();
	      }
	      writer.endArray();
	      writer.close();
	   }
	
	
	public static String parseDescription(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements checkEmpty = document.select("#ca-edit > a");
            if (checkEmpty.text().contains("Tạo mã nguồn")) {
                return "Không rõ";
            }
            Elements elements = document.select("#mw-content-text > div.mw-parser-output > p");
            Elements checkContent = document.select("#mw-content-text > div.mw-parser-output > p.mw-empty-elt");
            String data;
            if (checkContent.isEmpty()) {
                data = elements.get(0).text().trim();
            } else {
                data = elements.get(1).text().trim();
            }
            String clean[] = data.split("(\\[)(\\b([0-9]|[1-9][0-9]|100)\\b)(\\])");
            data = "";
            for (int i = 0; i < clean.length; i++) {
                data += clean[i];
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return "Không rõ";
        }
    }
}

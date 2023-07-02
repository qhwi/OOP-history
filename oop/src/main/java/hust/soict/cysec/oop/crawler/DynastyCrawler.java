package hust.soict.cysec.oop.crawler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.stream.JsonWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DynastyCrawler {
	public static void main(String[] args) throws IOException {
		String url = "https://vi.wikipedia.org/wiki/L%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
		Document document = Jsoup.connect(url).get();
		Element table = document.selectFirst("table.toccolours");
		Elements rows = table.select("tr");
		
		// GET DYNASTY's URLs FROM MAIN PAGE
		List<String> dynasty = new ArrayList<String>();
		for (int i = 2; i < rows.size() - 4; i++) {
			Elements tmp = rows.get(i).select("a[href]");
			if (tmp != null)
				for (Element t : tmp) {
					String dynastyURL = t.absUrl("href");
					Element font = rows.get(i).selectFirst("font");
					if (!dynasty.contains(dynastyURL))
						if (!t.text().contains("–") && font != null)
							dynasty.add(dynastyURL);
				}
		}
		OutputStream out = new FileOutputStream("src\\main\\json\\Dynasty.json");
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
		writer.setIndent("  ");
		writer.beginArray();
		// GET DYNASTY's INFO FROM URLs
		for (int i = 0; i < dynasty.size() - 1; i++) {
			int result = 0;
			String[] obj = new String[4];
			System.out.println(dynasty.get(i));
			document = Jsoup.connect(dynasty.get(i)).get();
			String name = document.selectFirst("span.mw-page-title-main").text();
			Elements texts = document.select("table.infobox > tbody > tr > td");
         
			// GET NAME, TIME
			if (texts.size() > 3) {
				String time = "";
				if (texts.get(0).text().contains("–") || texts.get(0).text().contains("-"))
					time = texts.get(0).text();
				else if (texts.get(1).text().contains("–") || texts.get(1).text().contains("-"))
					time = texts.get(1).text();
				else if (texts.get(3).text().contains("–") || texts.get(3).text().contains("-"))
					time = texts.get(3).text();
				else if (texts.get(2).text().contains("–") || texts.get(2).text().contains("-"))
					time = texts.get(2).text();
				if (!time.equals("")) {
					obj[0] = name;
					time = time.replace("← ", "").replace("→", "").replace("–", "-");
					String[] arr = time.split("-");
					try {
						int foundcount = 0;
						for (int j = 0; j < arr.length && foundcount < 2; j++) {
							String s = arr[j];
							Matcher matcher = Pattern.compile("\\d+").matcher(s);
							int num = 0;
							if (matcher.find()) {
								num = Integer.parseInt(matcher.group());
								foundcount++;
							} else {
								continue;
							}
							if (s.contains("TCN") || s.toLowerCase().contains("trước công nguyên")) {
								obj[foundcount] = Integer.toString(num) + " TCN";
							} else {
								obj[foundcount] = Integer.toString(num);
							}
						}
					} catch (java.lang.NumberFormatException e) {
						obj[1] = "?";
						obj[2] = "?";
					}             
					result += 3;
				}
			}
			//GET CAPITAL
			String capital = "";
			rows = document.select("table.infobox > tbody > tr");
			for (Element row : rows) {
				Element tmp = row.selectFirst("a[href]");
				if (tmp != null) {
					if (tmp.attr("title").equals("Thủ đô")) {
						capital = row.selectFirst("td").text();
						obj[3] = capital;
						result++;
						break;
					}
				}
			}
			//GET KINGS
			List<String> kings = new ArrayList<String>();
			Elements kingRows = document.select("tr.mergedrow");
			for (int j = 2; j < kingRows.size(); j++) {
				if (kingRows.get(j).text().equals(""))
					break;
				else {
					String king_name = kingRows.get(j).text().replace("• ", "");
					if (king_name.contains("Giải thể"))
						continue;
					king_name = king_name.replaceAll("\\s*\\([^)]+\\)", "");
					kings.add(king_name);
				}
			}
			
			//WRITE
			if (result > 3) {
				writer.beginObject();
				writer.name("name").value(obj[0]);
				writer.name("startYear").value(obj[1]);
				writer.name("endYear").value(obj[2]);
				writer.name("capital").value(obj[3]);
				writer.name("king");
				writer.beginArray();
				for (String king : kings)
					writer.value(king);
				writer.endArray();

				writer.endObject();
			}
		}
		writer.endArray();
		writer.close();
		out.close();
	}
}
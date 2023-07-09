package hust.soict.cysec.oop.linker;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LinkFigureToDynasty {
    private static String[][] DYNASTY_ALIAS = {{"Nhà Lê sơ","Nhà Tiền Lê"}, {"Nhà Nguyễn","Pháp đô hộ"}, {"Thời kỳ Bắc thuộc lần thứ hai", "Bắc thuộc lần 2"}, {"Nhà Mạc","Nam - Bắc Triều"}, {"Thời kỳ Bắc thuộc lần thứ nhất", "Bắc thuộc lần 1"}, {"Nhà Hậu Trần", "Thuộc Minh"}, {"Nhà Lê sơ","Triều Lê Sơ"}, {"Thời kỳ Bắc thuộc lần thứ ba","Bắc thuộc lần lần 3"}};
    public static void main(String[] args) throws IOException {
        JSONParser parser = new JSONParser();
        try {
            JSONArray data = parser.parse(new FileReader("src\\main\\json\\Figures.json"));
            JSONParser linkparser = new JSONParser();
            JSONArray dynasty_to_link = linkparser.parse(new FileReader("src\\main\\json\\Dynasty.json"));
            for (Object o : data) {
                JSONObject record = (JSONObject) o;
                JSONArray dynasties = (JSONArray) record.get("dynasties");
                record.put("relatedId", new JSONArray());
                for (int i = 0; i < dynasties.size(); i++) {
                    String dynasty = dynasties.get(i).toString().toLowerCase();
                    //Check empty dynasty
                    if (dynasty.length() == 0 || dynasty.equals("null")) {
                        dynasties.remove(i);
                        i--;
                        continue;
                    }
                    for (String[] alias : DYNASTY_ALIAS) {
                        if (dynasty.equalsIgnoreCase(alias[1])) {
                            dynasty = alias[0].toLowerCase();
                            break;
                        }
                    }
                    for (Object o2 : dynasty_to_link) {
                        JSONObject dlink = (JSONObject) o2;
                        if (dlink.getString("name").toLowerCase().contains(dynasty) || dynasty.contains(dlink.getString("name").toLowerCase())) {
                            ((JSONArray) record.get("relatedId")).add(dlink.getString("id"));
                            break;
                        }
                    }
                }
            }
            FileWriter file = new FileWriter("src\\main\\json\\linked\\Figures.json");
            file.write(data.toJSONString());
            file.flush();
            file.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
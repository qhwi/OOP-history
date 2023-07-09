package hust.soict.cysec.oop.linker;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LinkDynastyToKing {
    public static void main(String[] args) throws IOException {
        JSONParser parser = new JSONParser();
        try {
            JSONArray data = parser.parse(new FileReader("src\\main\\json\\Dynasty.json"));
            JSONParser linkparser = new JSONParser();
            JSONArray king_to_link = linkparser.parse(new FileReader("src\\main\\json\\linked\\Kings.json"));
            for (Object o : data) {
                JSONObject record = (JSONObject) o;
                JSONArray kingNames = (JSONArray) record.get("kings");
                record.put("relatedId", new JSONArray());
                for (int i = 0; i < kingNames.size(); i++) {
                    String king = kingNames.get(i).toString().toLowerCase();
                    for (Object o2 : king_to_link) {
                        JSONObject klink = (JSONObject) o2;
                        if (king.contains(klink.getString("name").toLowerCase()) || king.contains(klink.getString("courtesyName").toLowerCase())) {
                            ((JSONArray) record.get("relatedId")).add(klink.getString("id"));
                            break;
                        }
                    }
                }
            }
            FileWriter file = new FileWriter("src\\main\\json\\linked\\Dynasty.json");
            file.write(data.toJSONString());
            file.flush();
            file.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
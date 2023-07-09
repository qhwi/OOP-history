package hust.soict.cysec.oop.linker;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LinkKingToLinkedFigure {
    public static void main(String[] args) throws IOException {
        JSONParser parser = new JSONParser();
        try {
            JSONArray data = parser.parse(new FileReader("src\\main\\json\\Kings.json"));
            JSONParser linkparser = new JSONParser();
            JSONArray figure_to_link = linkparser.parse(new FileReader("src\\main\\json\\linked\\Figures.json"));
            for (Object o : data) {
                JSONObject record = (JSONObject) o;
                record.put("relatedId", new JSONArray());
                for (Object o2 : figure_to_link) {
                    JSONObject flink = (JSONObject) o2;
                    String ogname = record.getString("name").toLowerCase();
                    String linkname = flink.getString("name").toLowerCase();
                    String ogalias = record.getString("courtesyName").toLowerCase();
                    String linkalias;
                    if (flink.isNull("alias")) {
                        linkalias = flink.getString("alias").toLowerCase();
                    } else {
                        linkalias = "@";
                    }
                    if (ogname.contains(linkname) || ogname.contains(linkalias) || ogalias.contains(linkname) || ogalias.contains(linkalias)) {
                        ((JSONArray) record.get("relatedId")).add(flink.get("relatedId"));
                        break;
                    }
                }
            }
            FileWriter file = new FileWriter("src\\main\\json\\linked\\Kings.json");
            file.write(data.toJSONString());
            file.flush();
            file.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

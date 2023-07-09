package hust.soict.cysec.oop.linker;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LinkEventToLinkedFigure {
    public static void main(String[] args) throws IOException {
        JSONParser parser = new JSONParser();
        try {
            JSONArray data = parser.parse(new FileReader("src\\main\\json\\Event.json"));
            JSONParser linkparser = new JSONParser();
            JSONArray figure_to_link = linkparser.parse(new FileReader("src\\main\\json\\linked\\Figures.json"));
            for (Object o : data) {
                JSONObject record = (JSONObject) o;
                JSONArray figureNames = (JSONArray) record.get("figures");
                record.put("relatedId", new JSONArray());
                for (int i = 0; i < figureNames.size(); i++) {
                    String figure = figureNames.get(i).toString().toLowerCase();
                    for (Object o2 : figure_to_link) {
                        String linkname = flink.getString("name").toLowerCase();
                        String linkalias;
                        if (flink.isNull("alias")) {
                            linkalias = flink.getString("alias").toLowerCase();
                        } else {
                            linkalias = "@";
                        }
                        JSONObject flink = (JSONObject) o2;
                        if (figure.contains(linkname) || figure.contains(linkalias)) {
                            ((JSONArray) record.get("relatedId")).add(flink.getString("id"));
                            break;
                        }
                    }
                }
            }
            FileWriter file = new FileWriter("src\\main\\json\\linked\\Event.json");
            file.write(data.toJSONString());
            file.flush();
            file.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

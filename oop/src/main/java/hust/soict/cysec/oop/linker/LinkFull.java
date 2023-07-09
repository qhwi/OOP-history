package hust.soict.cysec.oop.linker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class LinkFull {
	public static void main(String[] args) {
		try {
            addId();
            System.out.println("[LINKING] Figure -> Dynasty");
			LinkFigureToDynasty.main(args);
            System.out.println("[LINKING] King -> Figure");
            LinkKingToLinkedFigure.main(args);
            System.out.println("[LINKING] Dynasty -> King");
            LinkDynastyToKing.main(args);
            System.out.println("[LINKING] Event -> Figure");
            LinkEventToLinkedFigure.main(args);
            System.out.println("[LINKING] Festival -> Figure");
            LinkFestivalToLinkedFigure.main(args);
		} catch (IOException e) {
			System.out.println("Error while linking objects");
		}
		
	}

    public static void addId() throws IOException {
        // ADD ID TO DYNASTY
        String json = new String(Files.readAllBytes(Paths.get("src\\main\\json\\Dynasty.json")));
        JSONArray data = new JSONArray(json);
        for (int i = 0; i < data.length(); i++) {
            JSONObject record = data.getJSONObject(i);
            if (record.isNull("id"))
                record.put("id", "#D" + i);
        }
        Files.write(Paths.get("src\\main\\json\\Dynasty.json"), data.toString().getBytes());

        // ADD ID TO FIGURE
        json = new String(Files.readAllBytes(Paths.get("src\\main\\json\\Figures.json")));
        data = new JSONArray(json);
        for (int i = 0; i < data.length(); i++) {
            JSONObject record = data.getJSONObject(i);
            if (record.isNull("id"))
                record.put("id", "#F" + i);
        }
        Files.write(Paths.get("src\\main\\json\\Figures.json"), data.toString().getBytes());

        // ADD ID TO KING
        json = new String(Files.readAllBytes(Paths.get("src\\main\\json\\Kings.json")));
        data = new JSONArray(json);
        for (int i = 0; i < data.length(); i++) {
            JSONObject record = data.getJSONObject(i);
            if (record.isNull("id"))
                record.put("id", "#K" + i);
        }
        Files.write(Paths.get("src\\main\\json\\Kings.json"), data.toString().getBytes());
    }
	
}

package hust.soict.cysec.oop.crawler.common;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JSONUtility {
	public static <T> List<T> readJson(String filePath, Class<T> type) {
        Gson gson = new Gson();
        List<T> objects = null;
        try {
            Type listType = TypeToken.getParameterized(List.class, type).getType();
            objects = gson.fromJson(new FileReader(filePath), listType);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the JSON file: " + e.getMessage());
        }
        return objects;
    }
	
	public static void writeToJSON(String path, Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(path));
			gson.toJson(obj, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

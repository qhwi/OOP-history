package hust.soict.cysec.oop.common;

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
	public static <T> List<T> readJson(String filePath, Class<T> type) throws IOException {
        Gson gson = new Gson();
        List<T> objects = null;
        Type listType = TypeToken.getParameterized(List.class, type).getType();
        objects = gson.fromJson(new FileReader(filePath), listType);
        return objects;
    }
	
	public static void writeToJSON(String path, Object obj) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer = new FileWriter(new File(path));
		gson.toJson(obj, writer);
		writer.close();
	}
}

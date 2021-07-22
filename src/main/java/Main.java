import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        String jsonFile = "new_data.json";

        String json = readString(jsonFile);

        List<Employee> list = jsonToList(json);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public static String readString(String jsonFile) {
        JSONParser jsonParser = new JSONParser();
        String json = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile))) {
            Object obj = jsonParser.parse(bufferedReader);
            json = obj.toString();

        } catch (IOException | ParseException exception) {
            System.out.println(exception.getMessage());
        }
        return json;
    }

    public static List<Employee> jsonToList(String json) throws ParseException {
        List<Employee> list = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(json);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        for (int i = 0; i < jsonArray.size(); i++) {
            Employee employee = gson.fromJson(jsonArray.get(i).toString(), Employee.class);
            list.add(employee);
        }
        return list;
    }
}

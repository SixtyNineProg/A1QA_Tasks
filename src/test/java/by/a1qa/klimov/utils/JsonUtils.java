package by.a1qa.klimov.utils;

import aquality.selenium.core.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object o) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            Logger.getInstance().error("can't represent object of class " +
                    o.getClass().getSimpleName() + " in json form for logging: " + e);
        }
        return json;
    }

    public static <T> T toObject(String jsonObject, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonObject, valueType);
        } catch (JsonProcessingException e) {
            Logger.getInstance().error("can't represent json form of class " +
                    valueType.getSimpleName() + " in object for logging: " + e);
            return null;
        }
    }

    public static boolean isStringJsonArray(String str) {
        try {
            new JSONArray(str);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static <T> List<T> toObjectsList(String jsonArrayT, Class<T> valueType) {
        List<T> objects = new ArrayList<>();
        if (jsonArrayT != null) {
            JSONArray jsonArray = new JSONArray(jsonArrayT);
            for (int i = 0; i < jsonArray.length(); i++) {
                T object = toObject(jsonArray.get(i).toString(), valueType);
                if (object != null) objects.add(object);
                else throw new NullPointerException("Post is empty.");
            }
        } else throw new NullPointerException("Json array is empty.");
        return objects;
    }
}

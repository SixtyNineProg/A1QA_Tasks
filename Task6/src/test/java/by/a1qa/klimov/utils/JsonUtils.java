package by.a1qa.klimov.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object o) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("can't represent object of class {} in json form for logging: {}",
                    o.getClass().getSimpleName(), e.toString());
        }
        return json;
    }

    public <T> T toObject(String jsonObject, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonObject, valueType);
        } catch (JsonProcessingException e) {
            log.error("can't represent json form of class {} in object for logging: {}",
                    valueType.getSimpleName(), e.toString());
            return null;
        }
    }
}

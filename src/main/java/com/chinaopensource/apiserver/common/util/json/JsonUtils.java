package com.chinaopensource.apiserver.common.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * create by lzl ON 2018/02/08
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        mapper.registerModule(module);
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> T readFromString(String json, Class<T> cls) {
        if(json == null || "".equals(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readFromBytes(byte[] bytes, Class<T> cls) {
        try {
            return mapper.readValue(bytes, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> readList(String json, Class<T> cls) {
        if(json == null || "".equals(json)) {
            return null;
        }
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, cls);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, Object> readMap(String json) {
        if(json == null || "".equals(json)) {
            return null;
        }
        JavaType javaType = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertValue(Object from, Class<T> cls) {
        return mapper.convertValue(from, cls);
    }


}

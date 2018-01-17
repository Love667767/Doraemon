package com.elson.basecore.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public class JsonUtil {

    private JsonUtil() {}

    private static final Gson G = new GsonBuilder().registerTypeAdapter(Double.class, new JsonSerializer<Double>() {

        public JsonElement serialize(Double src, Type typeOfSrc,
                                     JsonSerializationContext context) {
            Integer value = (int) Math.round(src);
            if (value.intValue() == src.doubleValue()) {
                return new JsonPrimitive(value);
            } else {
                return new JsonPrimitive(src);
            }
        }
    }).disableHtmlEscaping().create();//.serializeNulls()



    public static <T> T fromJson(String json, Class<T> classOfT) {
        if (TextUtils.isEmpty(json) || TextUtils.equals("null", json)) {
            return null;
        }
        try {
            return G.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public static <T> T fromJson(String json, Type type) {
        if (TextUtils.isEmpty(json) || TextUtils.equals("null", json)) {
            return null;
        }
        return G.fromJson(json, type);
    }

    public static <T> String toJson(T t) {
        return G.toJson(t);
    }

    /**
     * 参数值转字符串
     *
     * @param jsonParam
     * @return
     */
    public static String paramValue2String(Object jsonParam) {
        if (jsonParam instanceof String) {
            return (String) jsonParam;
        } else {
            return toJson(jsonParam);
        }
    }
}

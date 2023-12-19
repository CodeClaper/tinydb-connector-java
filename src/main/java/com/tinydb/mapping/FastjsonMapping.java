package com.tinydb.mapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tinydb.annotation.TableColumn;
import com.tinydb.exceptions.TinyDbParseException;
import com.tinydb.utils.Assert;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FastjsonMapping implements Mapping {
    @Override
    public <T> T mapping(String text, Class<T> clazz) {
        /* Check if arrayed, only allow one data or null. */
        if (text.startsWith("[") && text.endsWith("]")) {
            List<JSONObject> list = JSONObject.parseArray(text, JSONObject.class);
            int size = list.size();
            Assert.isTrue(size <= 1, String.format("Excepted one but get %d", list.size()));
            return size == 1 ? this.substitute(list.get(0), clazz) : null;
        } else if (text.startsWith("{") && text.endsWith("}"))
            return this.substitute(JSONObject.parseObject(text, JSONObject.class), clazz);
        else
            throw new TinyDbParseException(String.format("Can`t parse text '%s' to json or json array.", text));
    }

    @Override
    public <T> List<T> batchMapping(String text, final Class<T> clazz) {
        List<JSONObject> list = JSONObject.parseArray(text, JSONObject.class);
        return list.stream()
                .map(it -> this.substitute(it, clazz))
                .collect(Collectors.toList());
    }

    /**
     * substitute for key
     *
     * @param obj obj
     * @param clazz clazz
     * @return substitute
     * @param <T> type
     */
    private <T> T substitute(Object obj, Class<T> clazz) {
        Map<String, String> fieldMap = fieldMapping(clazz);
        JSONObject newJson = new JSONObject();
        JSONObject json = JSON.parseObject(JSONObject.toJSONString(obj));
        for (String key : json.keySet()) {
            if (fieldMap.containsKey(key)) {
                String newKey = fieldMap.get(key);
                newJson.put(newKey, json.get(key));
            }
        }
        return JSONObject.parseObject(JSONObject.toJSONString(newJson), clazz);
    }

    @SneakyThrows
    private Map<String, String> fieldMapping(Class<?> clazz) {
        Map<String, String> fieldMap = new LinkedHashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(TableColumn.class)) {
                TableColumn tableColumn = field.getAnnotation(TableColumn.class);
                fieldMap.put(tableColumn.value(), field.getName());
            } else
                fieldMap.put(field.getName(), field.getName());
        }
        return fieldMap;
    }
}

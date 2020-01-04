package com.example.demo.boot.common;

import java.util.HashMap;
import java.util.Map;

/**
 * url参数装填
 *
 * @author: 04637@163.com
 * @date: 2019/12/17
 */
public class Params {
    private Map<String, String> map = new HashMap<>();

    public Params add(String key, String val) {
        map.put(key, val);
        return this;
    }

    public String build() {
        StringBuilder paramUrl = new StringBuilder("?");
        for (Map.Entry<String, String> e : map.entrySet()) {
            paramUrl.append(e.getKey()).append("=").append(e.getValue()).append("&");
        }
        return paramUrl.toString();
    }
}

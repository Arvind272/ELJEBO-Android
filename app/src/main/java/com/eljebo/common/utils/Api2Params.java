package com.eljebo.common.utils;

import org.json.JSONObject;

import java.util.HashMap;

public class Api2Params {

    private HashMap requestParentMap;
    private HashMap requestChildMap;

    public Api2Params() {
        requestParentMap = new HashMap();
        requestChildMap = new HashMap();
    }

    public void put(String key, Object value) {
        requestChildMap.put(key, value);
    }

    public JSONObject getServerObject(String parent) {
        requestParentMap.put(parent, getChildObject());
        return new JSONObject(requestParentMap);
    }

    public JSONObject getChildObject() {
        return new JSONObject(requestChildMap);
    }

}

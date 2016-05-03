package com.netcosports.studytest;

import org.json.JSONObject;

/**
 * Created by Шикунец on 07.04.2016.
 */
public class ModJson {
     public final String modified;

    public ModJson(JSONObject jsonObject){
        modified = jsonObject.optString("modified");
    }

}

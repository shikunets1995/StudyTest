package com.netcosports.studytest;

import org.json.JSONObject;

/**
 * Created by Шикунец on 07.04.2016.
 */
public class CountryJson {

    public final String code;
    public final String flag;
    public final int population;

    public CountryJson(String code, String flag, int population) {
        this.code = code;
        this.flag = flag;
        this.population = population;
    }

    public CountryJson(JSONObject jsonObject) {
        code = jsonObject.optString("code");
        flag = jsonObject.optString("flag");
        population = jsonObject.optInt("population");
       // modified = jsonObject.optString("modified");
    }
}

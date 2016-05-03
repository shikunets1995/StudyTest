package com.netcosports.studytest.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.gesture.Prediction;
import android.preference.PreferenceManager;

public class PrefsUtils {

    public static final String KEY_JSON = "key_json";
    public static final String KEY_DATE = "key_date";
    public static  final String SWITCH_STATUS= "switch_status";

    public static void saveCountryJson(Context context, String json) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit()
                .putString(KEY_JSON, json)
                .apply();
    }
    public  static void saveDateJson(Context context, String date){
        SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit()
                .putString(KEY_DATE, date)
                .apply();
    }

    public static String getCountryJson(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(KEY_JSON, null);

    }

    public static String getDateJson(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getString(KEY_DATE, null);
    }

    public static boolean getSwitchStatus(Context switchStatus) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(switchStatus);
        return prefs.getBoolean(SWITCH_STATUS, true);

    }

    public  static void saveSwitchStatus(Context context, boolean switchStatus){
        SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit()
                .putBoolean(SWITCH_STATUS, switchStatus)
                .apply();
        }
}

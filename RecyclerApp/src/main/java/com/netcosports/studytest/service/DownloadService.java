package com.netcosports.studytest.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.netcosports.studytest.Country;
import com.netcosports.studytest.CountryJson;
import com.netcosports.studytest.activity.DbActivity;
import com.netcosports.studytest.db.DbContract;
import com.netcosports.studytest.db.DbHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Шикунец on 20.04.2016.
 */
public class DownloadService extends IntentService {

    public static void launch(Context context) {
        context.startService(new Intent(context,DownloadService.class));
    }


    public DownloadService() {
        super(DownloadService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {

            String response = downloadUrl("http://sc.ignitiondomain.com/denis_sh2/messages/temp.json");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray countriesArray = jsonObject.optJSONArray("countries");
            for (int i=0;i<=countriesArray.length();++i){
                CountryJson countryJson = new CountryJson(countriesArray.optJSONObject(i));
                ContentValues values = new ContentValues();
                values.put(DbHelper.COUNTRY, countryJson.code );
                values.put(DbHelper.CAPITAL,countryJson.flag);
                values.put(DbHelper.POPULATION, countryJson.population);
                getContentResolver().insert(DbContract.County.URI,values);
            }


        } catch (Exception e) {
            //    return "Unable to retrieve web page. URL may be invalid.";
        }
        getContentResolver().notifyChange(DbContract.County.URI, null);
    }

    private String downloadUrl(String myurl) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(myurl)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }
}

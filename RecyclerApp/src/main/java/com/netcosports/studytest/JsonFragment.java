package com.netcosports.studytest;
import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.netcosports.studytest.db.DbHelper;
import com.netcosports.studytest.util.PrefsUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class JsonFragment extends Fragment{

    JsonAdapter jsonAdapter= new JsonAdapter();
    final String LOG_TAG="myLogs";
    private TextView textView;
     SwipeRefreshLayout swipeRefreshLayout ;


    public static JsonFragment newInstance(){
        Bundle args= new Bundle();
        JsonFragment fragment=new JsonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        textView = (TextView) view.findViewById(R.id.textView1);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DownloadWebpageTask downloadWebpageTask = new DownloadWebpageTask();
                downloadWebpageTask.execute("http://sc.ignitiondomain.com/denis_sh2/messages/temp.json");
            }
        });

        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(jsonAdapter);

        String cachedJson = PrefsUtils.getCountryJson(getContext());
        jsonAdapter.setData(parsJson(cachedJson));

        String modified = PrefsUtils.getDateJson(getContext());
        textView.setText(modified);

        DownloadWebpageTask downloadWebpageTask = new DownloadWebpageTask();
        downloadWebpageTask.execute("http://sc.ignitiondomain.com/denis_sh2/messages/temp.json");
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
                protected String doInBackground(String... urls) {

                    try {
                        return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            swipeRefreshLayout.setRefreshing(false);
            List<CountryJson> countryJson = parsJson(s);
            if (!countryJson.isEmpty()) {
                DbHelper dbHelper = DbHelper.getInstance(getContext());
                for (CountryJson country : countryJson) {
                    ContentValues values =  new ContentValues();
                    values.put(DbHelper.COUNTRY, country.code);
                    values.put(DbHelper.CAPITAL, country.flag);
                    values.put(DbHelper.POPULATION, country.population);
                    long result = dbHelper.getWritableDatabase().insert(DbHelper.TABLE_COUNTRIES, null, values);
                    Log.i("zzz", "result for " + country.code + " is " + result);

                }


                jsonAdapter.setData(countryJson);
            }
            try {

                JSONObject jsonObject = new JSONObject(s);
                String modified = jsonObject.optString("modified");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm ZZZZZ");
                Date time = new Date();
                try {
                    time = simpleDateFormat.parse(modified);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat("yyyy MMMM dd EEEE  HH:mm ZZZZZ");

                textView.setText(newSimpleDateFormat.format(time));

                PrefsUtils.saveCountryJson(getContext(), s);
                PrefsUtils.saveDateJson(getContext(), modified);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    private List<CountryJson> parsJson(String data) {
        List<CountryJson> countryJsons = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray countriesArray = jsonObject.optJSONArray("countries");



            if (countriesArray != null) {

                for (int i = 0; i < countriesArray.length(); ++i) {
                    JSONObject json = countriesArray.optJSONObject(i);
                    countryJsons.add(new CountryJson(json));
                }
            }
        } catch (Exception e) {
        }

        return countryJsons;

    }


    private String downloadUrl(String myurl) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(myurl)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "FlagFragment onCreateView");
        return inflater.inflate(R.layout.fragment_0, container, false);
    }
}

package com.netcosports.studytest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TimeFragment extends Fragment {
    private static final String DEBUG_TAG = "HttpExample";
    final String LOG_TAG = "myLogs";

    private TextView textView;
    private Button button;


    public static TimeFragment newInstance() {
        Bundle args = new Bundle();
        TimeFragment fragment = new TimeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public void myClickHandler(View view) {

        String stringUrl = "http://time.akamai.com/";

        ConnectivityManager connMgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(stringUrl);
        } else {
            textView.setText("No network connection available.");
        }
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
        protected void onPostExecute(String result) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");

            long time = Long.parseLong(result) * DateUtils.SECOND_IN_MILLIS;
            textView.setText(simpleDateFormat.format(time));
        }
    }

    private String downloadUrl(String myurl) throws IOException {
//        InputStream is = null;
//        int len = 500;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(myurl)
                          .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }


    private void parsJson(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray countriesArray = jsonObject.optJSONArray("countries");
            if (countriesArray != null) {
                for (int i = 0; i < countriesArray.length(); ++i) {
                    JSONObject json = countriesArray.optJSONObject(i);
                }
            }
        } catch (Exception e) {
        }



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.textView1);            //EditText editText = (EditText) view.findViewById(R.id.editBox1);
       /* editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button.setEnabled(count > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickHandler(v);

            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "FlagFragment onCreateView");
        return inflater.inflate(R.layout.list_item_time, container, false);
    }
}

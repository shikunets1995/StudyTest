package com.netcosports.studytest.activity;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.netcosports.studytest.CountryJson;
import com.netcosports.studytest.JsonAdapter;
import com.netcosports.studytest.R;
import com.netcosports.studytest.db.DbContract;
import com.netcosports.studytest.db.DbHelper;
import com.netcosports.studytest.service.DownloadService;
import com.netcosports.studytest.util.PrefsUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Шикунец on 15.04.2016.
 */
public class DbActivity extends AppCompatActivity {

    JsonAdapter jsonAdapter= new JsonAdapter();

    public static Intent getLaunchIntent(Context context ) {
        return new Intent(context, DbActivity.class);
    }

    private int CONTRY_CODE = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(jsonAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_navi);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.smth){
                    Toast.makeText(DbActivity.this, "Something happend", Toast.LENGTH_SHORT).show();
                    return true;
                }else if(item.getItemId()==R.id.update){
                    DownloadService.launch(DbActivity.this);


//                    DownloadWebpageTask downloadWebpageTask = new DownloadWebpageTask();
//                    downloadWebpageTask.execute("http://sc.ignitiondomain.com/denis_sh2/messages/temp.json");
                    return  true;
                }
               else return false;
            }
        });
        //dbHelper = new DbHelper(this, "countries.db", null, 1)
        TextView textView = (TextView) findViewById(R.id.textView1);

        getSupportLoaderManager().initLoader(CONTRY_CODE, null, loaderCallbacks);


    }

    private LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            if (id == CONTRY_CODE) {
                String[] columns = new String[]{DbHelper.CAPITAL, DbHelper.COUNTRY, DbHelper.POPULATION };
                return new CursorLoader(getApplicationContext(), DbContract.County.URI, columns,null, null, null );
            }
            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            if (loader.getId() == CONTRY_CODE) {
                List<CountryJson> countryJsons = new ArrayList<>();
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            String name = cursor.getString(cursor.getColumnIndex(DbHelper.COUNTRY));
                            String flag = cursor.getString(cursor.getColumnIndex(DbHelper.CAPITAL));
                            int population = cursor.getInt(cursor.getColumnIndex(DbHelper.POPULATION));

                            countryJsons.add(new CountryJson(name, flag, population));


                        } while (cursor.moveToNext());
                    }
                }
                Log.i("zzz", "loaded: " + countryJsons);
                jsonAdapter.setData(countryJsons);
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };


}

package com.netcosports.testprovider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Adapter adapter = new Adapter();
    final String LOG_TAG="myLogs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);


        getSupportLoaderManager().initLoader(1, null, loaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Uri uri = new Uri.Builder()
                    .scheme("content")
                    .authority("com.netcosports.studytest.countries")
                    .path("country")
                    .appendQueryParameter("limit", "6")
                    .build();
            return new CursorLoader(getApplicationContext(), uri, null, null, null, "populaton asc");
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

            List<Country> country = new ArrayList<>();
            if(data != null){
                if (data.moveToFirst()){
                    do {
                        String code = data.getString(data.getColumnIndex("country"));
                        String flag = data.getString(data.getColumnIndex("capital"));
                        String population = data.getString(data.getColumnIndex("populaton"));
                        country.add(new Country(code, flag, population));

                    }while (data.moveToNext());
                }
            }
            Log.d("zzz", "" + data.getCount());
            adapter.setData(country);


        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.update){

            sendBroadcast(new Intent("asdsa"));
        }
        return true;
    }

}

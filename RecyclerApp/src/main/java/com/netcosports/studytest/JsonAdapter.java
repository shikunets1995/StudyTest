package com.netcosports.studytest;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.netcosports.studytest.db.DbHelper;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Шикунец on 07.04.2016.
 */
public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.Holder> {



    private List<CountryJson> data;
    String current ;
    private DbHelper dbHelper;

    public void setData(List<CountryJson> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public JsonAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_json, parent, false);
        return new Holder(view);
    }

    @Override

    public void onBindViewHolder(JsonAdapter.Holder holder, int position) {
       final CountryJson countryJson = data.get(position);

        holder.checkBox.setChecked(TextUtils.equals(current, countryJson.code));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = countryJson.code;
                notifyDataSetChanged();
            }
        });

        Context context = holder.itemView.getContext();
        holder.textView.setText(getCountryName(context, countryJson.code));
        holder.textView2.setText(String.valueOf(countryJson.population));
       // holder.textViewDB.setText(getCountryName(context, ));
        Picasso.with(context).load(countryJson.flag).into(holder.image);



    }

    private String getCountryName(Context context, String code) {

        if (TextUtils.equals(code, "blr")) {
            return context.getString(R.string.country_belarus);
        }
        if (TextUtils.equals(code, "can")) {
            return context.getString(R.string.country_canada);
        }
        if (TextUtils.equals(code, "tpe")) {
            return context.getString(R.string.country_tpe);
        }
        if (TextUtils.equals(code, "bra")) {
            return context.getString(R.string.country_brazil);
        }

        return code;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }


    class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        ImageView image;

        CheckBox checkBox;




        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView1);

            textView2 = (TextView) itemView.findViewById(R.id.textView2);

            image = (ImageView) itemView.findViewById(R.id.imageView);

            checkBox= (CheckBox) itemView.findViewById(R.id.checkbox1);


        }
    }
}




//Log.i("zzz", cursor.getColumnName(i) + " : " + cursor.getString(i));
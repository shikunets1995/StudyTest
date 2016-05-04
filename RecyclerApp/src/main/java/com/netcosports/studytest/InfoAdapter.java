package com.netcosports.studytest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шикунец on 03.05.2016.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.Holder>{

    private List<String> data ;


    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();


    }

    private Country country;

    public void setData(Country country) {
        this.country = country;
        notifyDataSetChanged();
    }

    @Override
    public InfoAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item_list3, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(InfoAdapter.Holder holder, int position) {
//        String item = data.get(position);
        Context context = holder.itemView.getContext();
        holder.textView.setText(country.name);

    }

    @Override
    public int getItemCount() {
//        if (data != null) {
//            return data.size();
//        }
        return 30;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView1);
        }

    }
}

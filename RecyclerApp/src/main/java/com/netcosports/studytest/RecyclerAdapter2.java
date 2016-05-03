package com.netcosports.studytest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Шикунец on 30.03.2016.
 */
public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.Holder> {

    private List<Country> data;

    private CountryClickListener countryClickListener;

    public void setCountryClickListener(CountryClickListener countryClickListener) {
        this.countryClickListener = countryClickListener;
    }

    public void setData(List<Country> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if (data != null) {
            return data.size();
        }

        return 0;
    }


    public RecyclerAdapter2.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_flag, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter2.Holder holder, int position) {

        final Country country = data.get(position);
        holder.textView.setText(country.name);
        holder.image.setImageResource(country.flagId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countryClickListener != null) {
                    countryClickListener.onCountryClicked(country);
                }
            }
        });
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView image;


        public Holder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView1);
            image = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

}

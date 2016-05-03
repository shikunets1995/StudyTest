package com.netcosports.testprovider;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Шикунец on 21.04.2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private List<Country> data;

    public  void setData(List<Country> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Country country = data.get(position);
        Context context = holder.itemView.getContext();

        holder.textView1.setText(country.code);
        holder.textView2.setText(String.valueOf(country.population));
        Picasso.with(context).load(country.flag).placeholder(R.drawable.placeholder_flag).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);

            textView1= (TextView) itemView.findViewById(R.id.textview1);
            textView2= (TextView) itemView.findViewById(R.id.textview2);
            imageView= (ImageView) itemView.findViewById(R.id.imageview);
        }
    }
}

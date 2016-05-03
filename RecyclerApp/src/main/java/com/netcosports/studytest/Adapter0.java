package com.netcosports.studytest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Шикунец on 30.03.2016.
 */
public class Adapter0 extends RecyclerView.Adapter<Adapter0.Holder> {
    public static final int TYPE_ALL = 0;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMAGE = 2;

    private List<String> data;

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_IMAGE;
        } else if (position % 5 == 0) {
            return TYPE_ALL;
        } else {
            return TYPE_TEXT;
        }
    }

    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_IMAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item_list2, parent, false);
        } else if (viewType == TYPE_ALL) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item_list1, parent, false);
            return new Holder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item_list3, parent, false);
            return new Holder(view);
        }
        return new Holder(view);
    }

    public void onBindViewHolder(Holder holder, int position) {
        int viewType = getItemViewType(position);
        String item = data.get(position);
        Context context = holder.itemView.getContext();

        if (viewType == TYPE_IMAGE) {

           // holder.image.setImageResource(R.mipmap.ic_launcher);
            Picasso.with(context).load("http://vsyo-tut.ru/images/stories/4hdf.jpg").into(holder.image);
        } else if (viewType == TYPE_ALL) {
            Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.image);
            holder.textView.setText("Picasso");
        } else {
            holder.textView.setText("ONLY TEXT");
            // holder.textView.setTextColor(position % 2 == 0 ? Color.BLUE : Color.RED);
        }
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





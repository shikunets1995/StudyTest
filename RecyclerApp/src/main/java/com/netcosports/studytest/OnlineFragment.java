package com.netcosports.studytest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Шикунец on 05.04.2016.
 */
public class OnlineFragment extends Fragment{
    final String LOG_TAG="myLogs";
    public static OnlineFragment newInstance() {
        Bundle args = new Bundle();
        OnlineFragment fragment = new OnlineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "online onCreateView");
        return inflater.inflate(R.layout.list_item_online, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        Picasso.with(getContext()).load("http://i.imgur.com/DvpvklR.png").into(imageView);
    }
}


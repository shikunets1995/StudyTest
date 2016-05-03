package com.netcosports.studytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Шикунец on 03.05.2016.
 */
public class CounrtryInfoActivity extends AppCompatActivity {

    private static final String EXTRA_COUNTRY = "country";

    public static Intent getLaunchIntent(Context context, Country country) {
        return new Intent(context, CounrtryInfoActivity.class)
                .putExtra(EXTRA_COUNTRY, country);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.country_info_activity);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        Country country = getIntent().getParcelableExtra(EXTRA_COUNTRY);

        toolbar.setTitle(country.name);
        imageView1.setImageResource(country.flagId);

//        imageView1.setImage




    }


}

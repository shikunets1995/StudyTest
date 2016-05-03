package com.netcosports.studytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements CountryClickListener {
    public static final String LOG_TAG = "Main";
    ImageView imageView;
    ImageButton imageButton;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        navigationView = (NavigationView) findViewById(R.id.navigation);
//        navigationView.setCheckedItem(R.id.home);

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                navigationView.setCheckedItem(item.getItemId());
//                drawerLayout.closeDrawer(GravityCompat.START);
//
//                Intent intent;
//                switch (item.getItemId()){
//                    case R.id.onliner:
//                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://onliner.by"));
//                        startActivity(intent);
//                        break;
//                    case R.id.tut:
//                        intent = new Intent(MainActivity.this, BrowserActivity.class);
//                        intent.setData(Uri.parse("http://tut.by"));
//                        startActivity  (intent);
//                        break;
//                    case R.id.db:
//                        intent = new Intent(MainActivity.this, DbActivity.class);
//                        startActivity(intent);
//                        break;
//
////
////                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://tut.by"));
////                        startActivity(intent);
////                        break;
//                }
//                return true;
//            }
//        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
     //   toolbar.setNavigationIcon(R.drawable.ua);
      //  toolbar.setNavigationIcon(R.drawable.brightness_3);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("Subtitle");


        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagesAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);



    }

//

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        else{

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.smth){

        }
//        if (item.getItemId() == R.id.add) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment0, Fragment0.newInstance("Hello"))
//                    .commit();
//        } else if (item.getItemId() == R.id.remove) {
//            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment0);
//            if (fragment != null) {
//                getSupportFragmentManager().beginTransaction()
//                        .remove(fragment)
//                        .commit();
//            } else {
//                Toast.makeText(this, "Already removed", Toast.LENGTH_SHORT).show();
//            }
//        }
        return true;
    }


    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "MainActivity onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "MainActivity onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "MainActivity onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "MainActivity onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MainActivity onDestroy");
    }


    @Override
    public void onCountryClicked(Country countryJson) {
        if (drawerLayout != null) {
            drawerLayout.closeDrawers();
        }

        startActivity(CounrtryInfoActivity.getLaunchIntent(this, countryJson));
        //TODO start new activity
    }
};


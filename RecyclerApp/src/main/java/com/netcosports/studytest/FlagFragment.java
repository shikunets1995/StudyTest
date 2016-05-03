package com.netcosports.studytest;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FlagFragment extends Fragment {
    final String LOG_TAG = "myLogs";

    private CountryClickListener countyClickListener;

    RecyclerAdapter2 adapter = new RecyclerAdapter2();

    public static FlagFragment newInstance() {

        Bundle args = new Bundle();

        FlagFragment fragment = new FlagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        recyclerView.setAdapter(adapter);
        adapter.setData(Country.getCountries());


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CountryClickListener) {
            countyClickListener = (CountryClickListener) context;
            adapter.setCountryClickListener(countyClickListener);
        }

    }

    @Override
    public void onDetach() {
        adapter.setCountryClickListener(null);
        countyClickListener = null;
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "FlagFragment onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "FlagFragment onCreateView");
        return inflater.inflate(R.layout.fragment_0, container, false);
    }


}


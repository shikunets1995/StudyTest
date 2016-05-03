package com.netcosports.studytest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.netcosports.studytest.util.PrefsUtils;


/**
 * Created by Шикунец on 05.04.2016.
 */
public class ButtonFragment extends Fragment {
    final String LOG_TAG = "myLogs";

    public static ButtonFragment newInstance() {
        Bundle args = new Bundle();
        ButtonFragment fragment = new ButtonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Button button;
    private SwitchCompat switchCompat;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText editText = (EditText) view.findViewById(R.id.editBox1);

        final boolean switchStatus = PrefsUtils.getSwitchStatus(getContext());
        editText.setEnabled(switchStatus);



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                button.setEnabled(count > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), editText.getText(), Toast.LENGTH_SHORT).show();

            }
        });
        switchCompat = (SwitchCompat) view.findViewById(R.id.switch1);
        switchCompat.setChecked(switchStatus);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                editText.setEnabled(isChecked);


                PrefsUtils.saveSwitchStatus(getContext(), isChecked);
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "FlagFragment onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "FlagFragment onCreateView");
        return inflater.inflate(R.layout.list_item_buttontext, container, false);
    }
}

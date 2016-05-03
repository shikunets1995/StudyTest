package co.eghfhxample.phonecontacts;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шикунец on 25.04.2016.
 */
public class ContactActivity extends AppCompatActivity {
    ContactAdapter contactAdapter = new ContactAdapter();
    SpinnerAdapter spinnerAdapter = new SpinnerAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contact_activity);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(contactAdapter);


        final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setAdapter(spinnerAdapter);
        spinner.setPrompt("My spinner");
                //spinner.setSelection(2);
//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getBaseContext(),position,Toast.LENGTH_SHORT).show();
//            }
//        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PhoneId phoneId = spinnerAdapter.getItem(position);
                Bundle args = new Bundle();
                args.putString("contact_id", phoneId.id);
                getSupportLoaderManager().restartLoader(1,args,loaderCallbacks);


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.d("zzz","" + getIntent().getStringExtra("id"));

//        getSupportLoaderManager().initLoader(1,null,loaderCallbacks);
        getSupportLoaderManager().initLoader(2,null,loaderCallbacks);

    }

    private LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            if (id == 1) {
                Uri uri = ContactsContract.Data.CONTENT_URI;
//                String idc = getIntent().getStringExtra("id");
                String ids = args.getString("contact_id");
//                return new CursorLoader(getApplicationContext(), uri, null, ContactsContract.Data.CONTACT_ID + " == " + idc, null, null);
                 return new CursorLoader(getApplicationContext(), uri, null, ContactsContract.Data.RAW_CONTACT_ID + " == " + ids, null, null);

            }

            if (id == 2) {
                String idc = getIntent().getStringExtra("id");
                return new CursorLoader(getApplicationContext(), ContactsContract.RawContacts.CONTENT_URI, null, ContactsContract.RawContacts.CONTACT_ID + " == " + idc, null, null);
            }
            return null;

        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            switch (loader.getId()) {
                case 1:
                    List<PhoneNumbers> numbers = new ArrayList<>();
                    List<PhoneAdress> adresses =new ArrayList<>();

                    if(cursor != null){
                        if (cursor.moveToFirst()){
                            do {
//                            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
////                            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.NAME_RAW_CONTACT_ID));
//                            contacts.add(new PhoneContacts(name,id));
                                Log.i("zzz", "" + cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DATA1)));
                                Log.i("zzz", "" + cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DATA2)));
                                String mimetype = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));

                                if (TextUtils.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, mimetype)){

                                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                    numbers.add(new PhoneNumbers(number));

                                }else if (TextUtils.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE, mimetype)){

                                    String adress = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                                    adresses.add(new PhoneAdress(adress));
                                }

//

                            }while (cursor.moveToNext());
                        }
                    }
                    contactAdapter.setData(numbers, adresses);
                    break;
                case 2:
                    List<PhoneId> ids=new ArrayList<>();
                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts._ID));
                            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY));
                            String account = cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE));

                            ids.add(new PhoneId(id, name + " " + account));
                        }while (cursor.moveToNext());
                    }
                    spinnerAdapter.setData(ids);
                    break;
            }


        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
}

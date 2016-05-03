package co.eghfhxample.phonecontacts;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PhoneContactsAdapter contactAdapter= new PhoneContactsAdapter();
    SpinnerAdapter spinnerAdapter = new SpinnerAdapter();
    String[] data = {"one", "two", "three", "four", "five"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(contactAdapter);
        getSupportLoaderManager().initLoader(1,null,loaderCallbacks);







    }


    private LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Uri uri = ContactsContract.Contacts.CONTENT_URI;

            return new CursorLoader(getApplicationContext(), uri, null, ContactsContract.Contacts.HAS_PHONE_NUMBER + " >0", null, ContactsContract.Contacts.DISPLAY_NAME + " asc ");
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

            List<PhoneContacts> contacts = new ArrayList<>();
            if(cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        contacts.add(new PhoneContacts(name,id));

                    }while (cursor.moveToNext());
                }
            }
            Log.d("zzz", "" + cursor.getCount());
            contactAdapter.setData(contacts);


        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
}

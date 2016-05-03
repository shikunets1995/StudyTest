package com.netcosports.studytest.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.netcosports.studytest.BuildConfig;

/**
 * Created by Шикунец on 20.04.2016.
 */
public class DbProvider extends ContentProvider {

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID+ ".countries";

    private DbHelper dbHelper;

    public static final int COUNTRIES = 100;

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(AUTHORITY, DbContract.County.PATH, COUNTRIES);
    }

    @Override
    public boolean onCreate() {
        dbHelper = DbHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int match =  sUriMatcher.match(uri);
        Log.i("zzz", "uri :" + uri);
        String limit = uri.getQueryParameter("limit");
        Cursor cursor = null;
        switch (match) {
            case COUNTRIES:
                cursor = dbHelper.getReadableDatabase().query(DbHelper.TABLE_COUNTRIES, projection, selection, selectionArgs, null, null, sortOrder, limit);
                break;
        }

        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
      long insertId;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)){
            case COUNTRIES:
                db.insertWithOnConflict(DbHelper.TABLE_COUNTRIES, null,values,SQLiteDatabase.CONFLICT_REPLACE);
                return null;

        }

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

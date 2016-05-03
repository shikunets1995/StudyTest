package com.netcosports.studytest.db;


import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class DbContract {

    private static final Uri BASE_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(DbProvider.AUTHORITY).build();

    public static final class County implements BaseColumns {
        public static final String PATH = "country";
        public static final Uri URI = Uri.withAppendedPath(BASE_URI, PATH);
    }
}

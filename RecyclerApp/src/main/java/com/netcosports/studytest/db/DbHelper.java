package com.netcosports.studytest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class DbHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "countries.db";
    public final static String TABLE_COUNTRIES = "countries";
    public static final String COUNTRY = "country";
    public static final String CAPITAL = "capital";
    public static final String POPULATION = "populaton";
    public static final int DB_VERSION = 3;
    private static DbHelper dbHelper;

    public static DbHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
        return dbHelper;
    }

    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static final String CREATE_TABLE_COUNTRIES = "create table " + TABLE_COUNTRIES + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + COUNTRY + " text unique not null, "
            + POPULATION + " int not null, "
            + CAPITAL + " text not null) ";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COUNTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
        onCreate(db);
    }
}

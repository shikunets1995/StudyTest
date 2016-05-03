package com.netcosports.studytest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Шикунец on 04.04.2016.
 */
public class Country implements Parcelable {

    public final String name;
    public final int flagId;

    public Country(String name, int flagId) {
        this.name = name;
        this.flagId = flagId;
    }

    protected Country(Parcel in) {
        name = in.readString();
        flagId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(flagId);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public static List<Country> getCountries() {
        return Arrays.asList(
                new Country("Australia", R.drawable.austr),
                new Country("Belarus", R.drawable.bel),
                new Country("Georgia", R.drawable.geo),
                new Country("Norway", R.drawable.norw),
                new Country("Poland", R.drawable.pol),
                new Country("Ukraine", R.drawable.ua),
                new Country("USA", R.drawable.us)
        );

    }
}

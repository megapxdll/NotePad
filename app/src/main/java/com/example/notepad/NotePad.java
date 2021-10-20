package com.example.notepad;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;


public class NotePad implements Parcelable{

    @StringRes
    private int name;

    @StringRes
    private int description;

    @StringRes
    private int date;

    public NotePad(int name, int description, int date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    protected NotePad(Parcel in) {
        name = in.readInt();
        description = in.readInt();
        date = in.readInt();
    }

    public static final Creator<NotePad> CREATOR = new Creator<NotePad>() {
        @Override
        public NotePad createFromParcel(Parcel in) {
            return new NotePad(in);
        }

        @Override
        public NotePad[] newArray(int size) {
            return new NotePad[size];
        }
    };

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

    public int getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(name);
        dest.writeInt(description);
        dest.writeInt(date);
    }

}

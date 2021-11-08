package com.example.notepad.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class NotePad implements Parcelable {

    private final int name;

    private final int description;

    public NotePad(int name, int description) {
        this.name = name;
        this.description = description;
    }

    protected NotePad(Parcel in) {
        name = in.readInt();
        description = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(name);
        dest.writeInt(description);
    }
}

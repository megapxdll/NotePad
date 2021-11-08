package com.example.notepad.domain;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.notepad.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefNotePadRepository implements NotePadRepository{

    private static final String ARG_NOTEPAD_LIST = "ARG_NOTEPAD_LIST";
    private SharedPreferences sharedPreferences;
    Type type = new TypeToken<ArrayList<NotePad>>() { }.getType();

    private Gson gson = new Gson();
    public SharedPrefNotePadRepository(Context context) {
        sharedPreferences = context.getSharedPreferences("SharedPrefNotePadRepository", Context.MODE_PRIVATE);
    }

    @Override
    public List<NotePad> getNotes() {
        String storedData = sharedPreferences.getString(ARG_NOTEPAD_LIST, "[]");

        ArrayList<NotePad> notePads = gson.fromJson(storedData, type);
        return notePads;
    }

    @Override
    public void add(String title, String message, Callback<NotePad> callback) {

        NotePad notePad = new NotePad(R.string.new_note, R.string.description);

        String storedData = sharedPreferences.getString(ARG_NOTEPAD_LIST, "[]");

        ArrayList<NotePad> notePads = gson.fromJson(storedData, type);

        notePads.add(notePad);

        sharedPreferences.edit().putString(ARG_NOTEPAD_LIST, gson.toJson(notePads, type)).apply();

        callback.onSuccess(notePad);
    }


    @Override
    public void delete(NotePad note) {

    }

    @Override
    public void clear() {

    }
}

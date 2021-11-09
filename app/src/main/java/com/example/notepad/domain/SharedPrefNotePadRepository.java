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
    private final List<NotePad> result = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    Type type = new TypeToken<ArrayList<NotePad>>() { }.getType();

    private Gson gson = new Gson();
    public SharedPrefNotePadRepository(Context context) {
        sharedPreferences = context.getSharedPreferences("SharedPrefNotePadRepository", Context.MODE_PRIVATE);
    }

    @Override
    public List<NotePad> getNotes() {
        result.add(new NotePad(R.string.note1, R.string.description1));
        result.add(new NotePad(R.string.note2, R.string.description2));
        result.add(new NotePad(R.string.note3, R.string.description3));
        result.add(new NotePad(R.string.note4, R.string.description4));
        result.add(new NotePad(R.string.note5, R.string.description5));
        String storedData = sharedPreferences.getString(ARG_NOTEPAD_LIST, result.toString());

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
    public void delete(NotePad note, Callback<Void> callback) {

    }

    @Override
    public void clear(Callback<Void> callback) {

    }
}

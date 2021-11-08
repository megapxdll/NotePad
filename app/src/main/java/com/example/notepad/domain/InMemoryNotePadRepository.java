package com.example.notepad.domain;

import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.notepad.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InMemoryNotePadRepository implements NotePadRepository {

    private final Executor executor = Executors.newSingleThreadExecutor();

    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    private final List<NotePad> result = new ArrayList<>();
    @Override
    public List<NotePad> getNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
            }
        });
        result.add(new NotePad(R.string.note1, R.string.description1));
        result.add(new NotePad(R.string.note2, R.string.description2));
        result.add(new NotePad(R.string.note3, R.string.description3));
        result.add(new NotePad(R.string.note4, R.string.description4));
        result.add(new NotePad(R.string.note5, R.string.description5));

        return result;
    }

    @Override
    public void add(String title, String message, Callback<NotePad> callback) {
        NotePad notePad = new NotePad(R.string.new_note, R.string.description);

        result.add(notePad);

        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(notePad);
            }
        });
    }


    @Override
    public void delete(NotePad note) {

    }

    @Override
    public void clear() {
        result.clear();

        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (new Random().nextBoolean()) {

                }
                else {

                }
            }
        });
    }
}

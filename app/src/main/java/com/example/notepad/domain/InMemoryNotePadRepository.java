package com.example.notepad.domain;

import android.os.Handler;
import android.os.Looper;

import com.example.notepad.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InMemoryNotePadRepository implements NotePadRepository {

    private final Executor executor = Executors.newSingleThreadExecutor();

    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public List<NotePad> getNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
            }
        });
        ArrayList<NotePad> result = new ArrayList<>();
        result.add(new NotePad(R.string.note1, R.string.description1));
        result.add(new NotePad(R.string.note2, R.string.description2));
        result.add(new NotePad(R.string.note3, R.string.description3));
        result.add(new NotePad(R.string.note4, R.string.description4));
        result.add(new NotePad(R.string.note5, R.string.description5));

        for (int i = 0; i < 100; i++) {
            result.add(new NotePad(R.string.note5, R.string.description5));
        }
        return result;
    }
}

package com.example.notepad.domain;

import com.example.notepad.R;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotePadRepository implements NotePadRepository {
    @Override
    public List<NotePad> getNotes() {
        ArrayList<NotePad> result = new ArrayList<>();
        result.add(new NotePad(R.string.note1, R.string.description1));
        result.add(new NotePad(R.string.note2, R.string.description2));
        result.add(new NotePad(R.string.note3, R.string.description3));
        result.add(new NotePad(R.string.note4, R.string.description4));
        result.add(new NotePad(R.string.note5, R.string.description5));
        return result;
    }
}

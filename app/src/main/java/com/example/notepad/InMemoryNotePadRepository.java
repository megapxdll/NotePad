package com.example.notepad;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotePadRepository implements NotePadRepository {

    @Override
    public List<NotePad> getNotes() {
        ArrayList<NotePad> result = new ArrayList<>();
        result.add(new NotePad(R.string.Заметка_1, R.string.task1, R.string.date1));
        result.add(new NotePad(R.string.Заметка_2, R.string.task2, R.string.date2));
        result.add(new NotePad(R.string.Заметка_3, R.string.task3, R.string.date3));
        result.add(new NotePad(R.string.Заметка_4, R.string.task4, R.string.date4));
        return result;
    }
}

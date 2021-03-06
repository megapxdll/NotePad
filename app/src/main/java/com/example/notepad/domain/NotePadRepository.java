package com.example.notepad.domain;

import java.util.List;

public interface NotePadRepository {

    List<NotePad> getNotes();

    void add(String title, String message, Callback<NotePad> callback);

    void delete(NotePad notePad, Callback<Void> callback);

    void clear(Callback<Void> callback);
}

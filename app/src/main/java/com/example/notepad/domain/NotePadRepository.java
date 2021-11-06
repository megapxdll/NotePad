package com.example.notepad.domain;

import java.util.List;

public interface NotePadRepository {

    List<NotePad> getNotes();

    void delete(NotePad note);
}

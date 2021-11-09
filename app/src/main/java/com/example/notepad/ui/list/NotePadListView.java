package com.example.notepad.ui.list;

import com.example.notepad.domain.NotePad;

import java.util.List;

public interface NotePadListView {

    void showNotePad(List<NotePad> notes);

    void addNotePad(NotePad notePad);

    void deleteNote(NotePad selectedNote);

    void clearNotes();
}

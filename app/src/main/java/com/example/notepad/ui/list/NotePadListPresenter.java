package com.example.notepad.ui.list;

import com.example.notepad.R;
import com.example.notepad.domain.Callback;
import com.example.notepad.domain.NotePad;
import com.example.notepad.domain.NotePadRepository;

public class NotePadListPresenter {

    private final NotePadListView view;

    private final NotePadRepository repository;

    public NotePadListPresenter(NotePadListView view, NotePadRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void clear(NotePad selectedNote) {

    }

    public void requestNotePad() {
        view.showNotePad(repository.getNotes());
    }

    public void add(String newNote, String enterText) {
        repository.add(newNote, enterText, new Callback<NotePad>() {
            @Override
            public void onSuccess(NotePad result) {

                view.addNotePad(result); //Test
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }
}

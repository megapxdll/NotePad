package com.example.notepad.ui.list;

import com.example.notepad.domain.NotePadRepository;

public class NotePadListPresenter {

    private final NotePadListView view;

    private final NotePadRepository repository;

    public NotePadListPresenter(NotePadListView view, NotePadRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotePad() {
        view.showNotePad(repository.getNotes());
    }
}

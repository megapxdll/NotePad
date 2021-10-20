package com.example.notepad;

public class NotePadListPresenter {

    private final NotePadListView view;

    private final NotePadRepository repository;

    public NotePadListPresenter(NotePadListView view, NotePadRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {
        view.showNotes(repository.getNotes());
    }
}

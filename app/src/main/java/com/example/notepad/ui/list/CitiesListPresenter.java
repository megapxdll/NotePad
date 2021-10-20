package com.example.notepad.ui.list;

import com.example.notepad.domain.CitiesRepository;

public class CitiesListPresenter {

    private final CitiesListView view;

    private final CitiesRepository repository;

    public CitiesListPresenter(CitiesListView view, CitiesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestCities() {
        view.showCities(repository.getCities());
    }
}

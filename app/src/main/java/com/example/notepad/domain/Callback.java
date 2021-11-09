package com.example.notepad.domain;

public interface Callback<T> {
    void onSuccess(T result);
    void onError(Throwable error);
}

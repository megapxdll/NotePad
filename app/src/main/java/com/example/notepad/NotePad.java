package com.example.notepad;

import com.google.type.DateTime;

public class NotePad {
    private String Name;
    private String Description;
    private DateTime Date;

    public NotePad(String name, String description, DateTime date) {
        Name = name;
        Description = description;
        Date = date;
    }

}

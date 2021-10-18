package com.example.notepad;

import com.google.type.DateTime;

public class NotePad {
    private String Name;
    private String Description;
    private String Date;

    public NotePad(String name, String description, String date) {
        Name = name;
        Description = description;
        Date = date;
    }

    public String getData() {
        return Name + ": " + Description + " (" + Date + ")";
    }

    public String getDescription() {
        return Description;
    }
}

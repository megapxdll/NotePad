package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotePadFragment notePadFragment = new NotePadFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, notePadFragment).commit();
    }
}
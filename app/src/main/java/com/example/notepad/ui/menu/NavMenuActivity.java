package com.example.notepad.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notepad.R;
import com.example.notepad.ui.MainActivity;
import com.example.notepad.ui.fm.FmActivity;

public class NavMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_menu);
    }
}

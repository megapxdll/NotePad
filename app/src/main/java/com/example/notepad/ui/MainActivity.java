package com.example.notepad.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.notepad.R;
import com.example.notepad.domain.NotePad;
import com.example.notepad.ui.details.NotePadDetailsActivity;
import com.example.notepad.ui.details.NotePadDetailsFragment;
import com.example.notepad.ui.list.NotePadListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String ARG_NOTE = "ARG_NOTE";

    private NotePad selectedNotePad;
    private FloatingActionButton fmOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbarAndDrawer();


        FragmentManager fragmentManager = getSupportFragmentManager();

        if (!(fragmentManager.findFragmentById(R.id.fragment_container) instanceof NotePadListFragment)) {
            fragmentManager.popBackStack();
        }

        boolean isLandscape = getResources().getBoolean(R.bool.is_landscape);

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_NOTE)) {
            selectedNotePad = savedInstanceState.getParcelable(ARG_NOTE);

            if (isLandscape) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_NOTE, selectedNotePad);

                fragmentManager.setFragmentResult(NotePadDetailsFragment.KEY_NOTES_LIST_DETAILS, bundle);
            }
        }

        getSupportFragmentManager().setFragmentResultListener(NotePadListFragment.KEY_NOTES_LIST_ACTIVITY, this, (requestKey, result) -> {

            selectedNotePad = result.getParcelable(NotePadListFragment.ARG_NOTE);

            if (isLandscape) {

                fragmentManager.setFragmentResult(NotePadDetailsFragment.KEY_NOTES_LIST_DETAILS, result);
            } else {

                Intent intent = new Intent(MainActivity.this, NotePadDetailsActivity.class);
                intent.putExtra(NotePadDetailsFragment.ARG_NOTE, selectedNotePad);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "Searching", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_sort:
                Toast.makeText(getApplicationContext(), "Sorting", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_drawer_about:
                showAboutAlertDialog();
                return true;
            case R.id.action_drawer_exit:
                showExitAlertDialog();
            case R.id.action_clear:

                Toast.makeText(getApplicationContext(), "Cleared", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedNotePad != null) {
            outState.putParcelable(ARG_NOTE, selectedNotePad);
        }
        super.onSaveInstanceState(outState);
    }


    private void initToolbarAndDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @SuppressLint("NonConstantResourceId")
    private void initDrawer(Toolbar toolbar) {

        DrawerLayout navigation_menu = (DrawerLayout) findViewById(R.id.navigation_menu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, navigation_menu, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        navigation_menu.addDrawerListener(toggle);
        toggle.syncState();

        // Обработка навигационного меню
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_drawer_about:
                    Toast.makeText(getApplicationContext(), "About window opened", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_drawer_exit:
                    Toast.makeText(getApplicationContext(), "Settings window opened", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_clear:
                    Toast.makeText(getApplicationContext(), "Cleared", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });
    }

    private void showAboutAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.about_title))
                .setMessage(getResources().getString(R.string.about_description))
                // Можно указать и пиктограмму
                //.setIcon(R.mipmap.ic_launcher_round)
                // Из этого окна нельзя выйти кнопкой Back
                //.setCancelable(false)
                .setNeutralButton(getResources().getString(R.string.back_button), null)
                .show();
    }

    private void showExitAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.exit_title))
                .setMessage(getResources().getString(R.string.exit_description))
                // Можно указать и пиктограмму
                //.setIcon(R.mipmap.ic_launcher_round)
                // Из этого окна нельзя выйти кнопкой Back
                //.setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes_button), (dialogInterface, i) -> Toast.makeText(MainActivity.this, "Yes!", Toast.LENGTH_SHORT).show())
                .setNegativeButton(getResources().getString(R.string.no_button), (dialogInterface, i) -> Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_SHORT).show())
                .setNeutralButton(getResources().getString(R.string.cancel_button), null)
                .show();
    }
}

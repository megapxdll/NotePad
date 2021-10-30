package com.example.notepad.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.notepad.R;
import com.example.notepad.domain.NotePad;
import com.example.notepad.ui.details.NotePadDetailsActivity;
import com.example.notepad.ui.details.NotePadDetailsFragment;
import com.example.notepad.ui.fm.FmActivity;
import com.example.notepad.ui.list.NotePadListFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String ARG_NOTE = "ARG_NOTE";

    private NotePad selectedNotePad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fm_options).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FmActivity.class);
                startActivity(intent);
            }
        });

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
            } else {
            }
        }

        getSupportFragmentManager().setFragmentResultListener(NotePadListFragment.KEY_NOTES_LIST_ACTIVITY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                selectedNotePad = result.getParcelable(NotePadListFragment.ARG_NOTE);

                if (isLandscape) {

                    fragmentManager.setFragmentResult(NotePadDetailsFragment.KEY_NOTES_LIST_DETAILS, result);
                } else {

                    Intent intent = new Intent(MainActivity.this, NotePadDetailsActivity.class);
                    intent.putExtra(NotePadDetailsFragment.ARG_NOTE, selectedNotePad);

                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {

        final DrawerLayout drawer = findViewById(R.id.navigation_menu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Обработка навигационного меню
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_drawer_about:
                        Toast.makeText(getApplicationContext(), "About window opened", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_drawer_exit:
                        Toast.makeText(getApplicationContext(), "Settings window opened", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }
}

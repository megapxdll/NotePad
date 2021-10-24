package com.example.notepad.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.notepad.R;
import com.example.notepad.domain.NotePad;
import com.example.notepad.ui.details.NotePadDetailsActivity;
import com.example.notepad.ui.details.NotePadDetailsFragment;
import com.example.notepad.ui.fm.FmActivity;
import com.example.notepad.ui.list.NotePadListFragment;

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
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedNotePad != null) {
            outState.putParcelable(ARG_NOTE, selectedNotePad);
        }
        super.onSaveInstanceState(outState);
    }
}

package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String ARG_NOTEPAD = "ARG_NOTEPAD";

    private NotePad selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (!(fragmentManager.findFragmentById(R.id.fragment_container) instanceof NotePadListFragment)) {
            fragmentManager.popBackStack();
        }

        boolean isLandscape = getResources().getBoolean(R.bool.is_landscape);

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_NOTEPAD)) {
            selectedNote = savedInstanceState.getParcelable(ARG_NOTEPAD);

            if (isLandscape) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_NOTEPAD, selectedNote);

                fragmentManager.setFragmentResult(NotePadDetailsFragment.KEY_NOTEPAD_LIST_DETAILS, bundle);
            } else {
            }
        }

        getSupportFragmentManager().setFragmentResultListener(NotePadListFragment.KEY_NOTEPAD_LIST_ACTIVITY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                selectedNote = result.getParcelable(NotePadListFragment.ARG_NOTEPAD);

                if (isLandscape) {

                    fragmentManager.setFragmentResult(NotePadDetailsFragment.KEY_NOTEPAD_LIST_DETAILS, result);
                } else {

                    Intent intent = new Intent(MainActivity.this, NotePadDetailsActivity.class);
                    intent.putExtra(NotePadDetailsFragment.ARG_NOTEPAD, selectedNote);

                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedNote != null) {
            outState.putParcelable(ARG_NOTEPAD, selectedNote);
        }
        super.onSaveInstanceState(outState);
    }

}
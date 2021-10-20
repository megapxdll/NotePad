package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import android.os.Bundle;

public class NotePadDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NotePad notePad = getIntent().getParcelableExtra(NotePadDetailsFragment.ARG_NOTEPAD);

        getSupportFragmentManager().setFragmentFactory(new NotePadDetailsFragmentFactory(notePad));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_pad_details);

        if (savedInstanceState == null) {


            Fragment cityDetailsFragment = new NotePadDetailsFragment(notePad);
            // cityDetailsFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, cityDetailsFragment)
                    .commit();
        }
    }

    static class NotePadDetailsFragmentFactory extends FragmentFactory {

        private final NotePad notePad;

        public NotePadDetailsFragmentFactory(NotePad notePad) {
            this.notePad = notePad;
        }

        @NonNull
        @Override
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
            if (className.equals(NotePadDetailsFragment.class.getName())) {
                return new NotePadDetailsFragment(notePad);
            }
            return super.instantiate(classLoader, className);
        }
    }
}
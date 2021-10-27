package com.example.notepad.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.example.notepad.R;
import com.example.notepad.domain.NotePad;

public class NotePadDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NotePad notePad = getIntent().getParcelableExtra(NotePadDetailsFragment.ARG_NOTE);

        getSupportFragmentManager().setFragmentFactory(new NotePadDetailsFragmentFactory(notePad));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notepad_details);

        if (savedInstanceState == null) {


            Fragment notePadDetailsFragment = new NotePadDetailsFragment(notePad);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_details_container, notePadDetailsFragment)
                    .commit();
        }

        findViewById(R.id.edit_text_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "text edit window opened", Toast.LENGTH_SHORT).show();
            }
        });
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

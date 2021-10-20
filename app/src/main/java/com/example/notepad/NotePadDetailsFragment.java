package com.example.notepad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class NotePadDetailsFragment extends Fragment {

    public static final String KEY_NOTEPAD_LIST_DETAILS = "KEY_NOTEPAD_LIST_DETAILS";
    public static final String ARG_NOTEPAD = "ARG_NOTEPAD";

    private NotePad notePad;

    public NotePadDetailsFragment(NotePad notePad) {
        super(R.layout.fragment_note_pad_details);
        this.notePad = notePad;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView description = view.findViewById(R.id.description);
        TextView name = view.findViewById(R.id.name);
        TextView date = view.findViewById(R.id.date);

        if (notePad != null) {

            description.setText(notePad.getDescription());

            name.setText(notePad.getName());

            date.setText(notePad.getDate());
        }

        getParentFragmentManager().setFragmentResultListener(NotePadDetailsFragment.KEY_NOTEPAD_LIST_DETAILS, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                NotePad notePad1 = result.getParcelable(ARG_NOTEPAD);

                description.setText(notePad1.getDescription());

                name.setText(notePad1.getName());

                date.setText(notePad1.getDate());
            }
        });

    }
}
package com.example.notepad.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.notepad.R;
import com.example.notepad.domain.NotePad;

public class NotePadDetailsFragment extends Fragment {
    public static final String ARG_NOTE = "ARG_NOTE";

    public static final String KEY_NOTES_LIST_DETAILS = "KEY_NOTES_LIST_DETAILS";

    private NotePad notePad;

    public NotePadDetailsFragment(NotePad notePad) {
        super(R.layout.fragment_notepad_details);
        this.notePad = notePad;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView description = view.findViewById(R.id.description);
        TextView name = view.findViewById(R.id.note_name);
        Button edit_text = view.findViewById(R.id.edit_text_button);

        if (notePad != null) {

            description.setText(notePad.getDescription());

            name.setText(notePad.getName());

            edit_text.setText(getResources().getString(R.string.Edit_text));
        }

        getParentFragmentManager().setFragmentResultListener(NotePadDetailsFragment.KEY_NOTES_LIST_DETAILS, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                NotePad notePad1 = result.getParcelable(ARG_NOTE);

                description.setText(notePad1.getDescription());

                name.setText(notePad1.getName());

                edit_text.setText(getResources().getString(R.string.Edit_text));
            }
        });

        edit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "new window opened", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.notepad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class NotePadListFragment extends Fragment implements NotePadListView{

    public static final String KEY_NOTEPAD_LIST_ACTIVITY = "KEY_NOTEPAD_LIST_ACTIVITY";
    public static final String ARG_NOTEPAD = "ARG_NOTEPAD";

    private LinearLayout notepadListRoot;

    private NotePadListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotePadListPresenter(this, new InMemoryNotePadRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_pad_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notepadListRoot = view.findViewById(R.id.notepad_root);

        presenter.requestNotes();
    }

    @Override
    public void showNotes(List<NotePad> notes) {

        for (NotePad note : notes) {
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, notepadListRoot, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ARG_NOTEPAD, note);

                    getParentFragmentManager()
                            .setFragmentResult(KEY_NOTEPAD_LIST_ACTIVITY, bundle);

//                    if (onCityClicked != null) {
//                        onCityClicked.onCityClicked(city);
//                    }
                }
            });

            TextView description = itemView.findViewById(R.id.description);
            description.setText(note.getDescription());

            TextView name = itemView.findViewById(R.id.name);
            name.setText(note.getName());

            TextView date = itemView.findViewById(R.id.date);
            date.setText(note.getDate());

            notepadListRoot.addView(itemView);
        }
    }
}
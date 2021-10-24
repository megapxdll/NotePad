package com.example.notepad.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notepad.R;
import com.example.notepad.domain.NotePad;
import com.example.notepad.domain.InMemoryNotePadRepository;

import java.util.List;

public class NotePadListFragment extends Fragment implements NotePadListView {

    public static final String KEY_NOTES_LIST_ACTIVITY = "KEY_NOTES_LIST_ACTIVITY";
    public static final String ARG_NOTE = "ARG_NOTE";

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
        return inflater.inflate(R.layout.fragment_notepad_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notepadListRoot = view.findViewById(R.id.notepad_root);

        presenter.requestNotePad();
    }

    @Override
    public void showNotePad(List<NotePad> notes) {

        for (NotePad notePad : notes) {
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, notepadListRoot, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ARG_NOTE, notePad);

                    getParentFragmentManager()
                            .setFragmentResult(KEY_NOTES_LIST_ACTIVITY, bundle);
                }
            });

            TextView description = itemView.findViewById(R.id.description);
            description.setText(notePad.getDescription());

            TextView name = itemView.findViewById(R.id.note_name);
            name.setText(notePad.getName());

            notepadListRoot.addView(itemView);
        }
    }
}

package com.example.notepad.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.legacy.widget.Space;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.domain.NotePad;
import com.example.notepad.domain.InMemoryNotePadRepository;
import com.example.notepad.ui.MainActivity;
import com.example.notepad.ui.fm.FmActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotePadListFragment extends Fragment implements NotePadListView {

    public static final String KEY_NOTES_LIST_ACTIVITY = "KEY_NOTES_LIST_ACTIVITY";
    public static final String ARG_NOTE = "ARG_NOTE";

    private LinearLayout notepadListRoot;

    private NotePadAdapter adapter;
    private NotePadListPresenter presenter;
    private FloatingActionButton fm_options_view;


    public NotePadListFragment() {
        super(R.layout.fragment_notepad_list);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotePadListPresenter(this, new InMemoryNotePadRepository());
        adapter = new NotePadAdapter();

        adapter.setNoteClicked(new NotePadAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(NotePad note) {
                Toast.makeText(requireContext(), note.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
/**
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notepad_list, container, false);
    }
 */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fm_options_view = (FloatingActionButton) view.findViewById(R.id.fm_options);

        fm_options_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FmActivity.class);
            }
        });

        notepadListRoot = view.findViewById(R.id.notepad_root);

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager lm = new GridLayoutManager(requireContext(), 2);

        notesList.setLayoutManager(lm);

        notesList.setAdapter(adapter);

        presenter.requestNotePad();
    }


    @Override
    public void showNotePad(List<NotePad> notes) {

        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
        /**
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
         */
    }
}

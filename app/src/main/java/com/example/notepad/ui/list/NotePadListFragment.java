package com.example.notepad.ui.list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.domain.FireStoreNotePadRepository;
import com.example.notepad.domain.NotePad;
import com.example.notepad.domain.SharedPrefNotePadRepository;
import com.example.notepad.ui.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarItemView;

import android.view.MenuItem;
import android.widget.Button;

import java.util.Collections;
import java.util.List;

public class NotePadListFragment extends Fragment implements NotePadListView {

    public static final String KEY_NOTES_LIST_ACTIVITY = "KEY_NOTES_LIST_ACTIVITY";
    public static final String ARG_NOTE = "ARG_NOTE";

    private FragmentActivity fragmentActivity;
    private NotePad selectedNotePad;

    private NotePadAdapter adapter;
    private NotePadListPresenter presenter;


    public NotePadListFragment() {
        super(R.layout.fragment_notepad_list);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotePadListPresenter(this, new FireStoreNotePadRepository());
        adapter = new NotePadAdapter(this);

        adapter.setNoteClicked(note -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(ARG_NOTE, note);

            getParentFragmentManager()
                    .setFragmentResult(KEY_NOTES_LIST_ACTIVITY, bundle);
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
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        //LinearLayout notepadListRoot = view.findViewById(R.id.notepad_root);

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        FloatingActionButton fmOptions = view.findViewById(R.id.fm_options);
        fmOptions.setOnClickListener(v -> presenter.add("new note", "enter text"));

        RecyclerView.LayoutManager lm = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager lm = new GridLayoutManager(requireContext(), 2);

        notesList.setLayoutManager(lm);

        notesList.setAdapter(adapter);

        presenter.requestNotePad();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedNotePad != null) {
            outState.putParcelable(ARG_NOTE, selectedNotePad);
        }
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showNotePad(List<NotePad> notes) {

        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void clearNotes() {
        adapter.setNotes(Collections.emptyList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addNotePad(NotePad result) {
        adapter.addNotePad(result);
        adapter.notifyItemInserted(adapter.getItemCount() - 1);
    }

    @Override
    public void deleteNote(NotePad selectedNote) {

        int position = adapter.deleteNote(selectedNote);

        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        requireActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            presenter.delete(selectedNotePad);
            return true;
        }

        if (item.getItemId() == R.id.action_edit) {
            return true;
        }

        return super.onContextItemSelected(item);
    }
}

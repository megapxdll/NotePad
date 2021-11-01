package com.example.notepad.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.domain.NotePad;

import java.util.ArrayList;
import java.util.Collection;

public class NotePadAdapter extends RecyclerView.Adapter<NotePadAdapter.NotePadViewHolder> {

    interface OnNoteClicked {
        void onNoteClicked(NotePad note);
    }

    private final ArrayList<NotePad> notes = new ArrayList<>();

    private OnNoteClicked noteClicked;

    public void setNotes(Collection<NotePad> toSet) {
        notes.clear();
        notes.addAll(toSet);
    }

    @NonNull
    @Override
    public NotePadAdapter.NotePadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        return new NotePadViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotePadAdapter.NotePadViewHolder holder, int position) {
        NotePad notePad = notes.get(position);

        holder.getName().setText(notePad.getName());
        holder.getDescription().setText(notePad.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public OnNoteClicked getNoteClicked() {
        return noteClicked;
    }

    public void setNoteClicked(OnNoteClicked noteClicked) {
        this.noteClicked = noteClicked;
    }

    class NotePadViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        private TextView description;

        public TextView getName() {
            return name;
        }

        public TextView getDescription() {
            return description;
        }

        public NotePadViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getNoteClicked() != null) {
                        getNoteClicked().onNoteClicked(notes.get(getAdapterPosition()));
                    }
                }
            });

            name = itemView.findViewById(R.id.note_name);
            description = itemView.findViewById(R.id.description);
        }
    }
}

package com.example.notepad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NotePadFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_pad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        NotePad notePad;
        String[] nodes_name = getResources().getStringArray(R.array.nodes_name);
        String[] nodes_desc = getResources().getStringArray(R.array.nodes_desc);
        String[] nodes_date = getResources().getStringArray(R.array.nodes_date);

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        for (int i = 0; i < nodes_name.length; i++) {
            notePad = new NotePad(nodes_name[i], nodes_desc[i], nodes_date[i]);
            TextView textView = new TextView(getContext());
            textView.setText(notePad.getData());
            textView.setTextSize(20);
            textView.setBackgroundResource(R.color.teal_700);
            layoutView.addView(textView);
        }
    }

}
package com.example.notepad.domain;

import androidx.annotation.NonNull;

import com.example.notepad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireStoreNotePadRepository implements NotePadRepository{
    private static final String NOTES = "notes";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    public List<NotePad> getNotes() {
        firebaseFirestore.collection(NOTES)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<DocumentSnapshot> documents = task.getResult().getDocuments();

                            ArrayList<NotePad> result = new ArrayList<>();

                            for (DocumentSnapshot document : documents) {

                                String title = document.getString(TITLE);
                                String description = document.getString(DESCRIPTION);


                                NotePad notePad = new NotePad(R.string.new_note, R.string.description);

                                result.add(notePad);
                            }
                        }
                    }
                });
        return null;
    }

    @Override
    public void add(String title, String message, Callback<NotePad> callback) {

        Map<String, Object> data = new HashMap<>();

        data.put(TITLE, title);
        data.put(DESCRIPTION, message);

        firebaseFirestore.collection(NOTES)
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful()) {
                            NotePad notePad = new NotePad(R.id.note_name, R.id.description);
                            callback.onSuccess(notePad);
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    @Override
    public void delete(NotePad notePad, Callback<Void> callback) {

    }

    @Override
    public void clear(Callback<Void> callback) {

    }
}

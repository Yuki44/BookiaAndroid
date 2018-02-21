package com.example.yuki.bookia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewBooksActivity extends AppCompatActivity {

    private Button bSaveButton;
    private EditText bBookTitle;
    private EditText bAuthorText;
    private EditText bYearText;

    private FirebaseFirestore bFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_books);

        bFirestore = FirebaseFirestore.getInstance();

        bBookTitle = findViewById(R.id.bookTitle);
        bAuthorText = findViewById(R.id.authorText);
        bYearText = findViewById(R.id.yearText);
        bSaveButton = findViewById(R.id.saveBtn);

        bSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String bookTitle = bBookTitle.getText().toString().trim();
                String authorText = bAuthorText.getText().toString().trim();
                String yearText = bYearText.getText().toString();

                if (!bookTitle.isEmpty() || !authorText.isEmpty() || !yearText.isEmpty()) {

                    Map<String, String> bookMap = new HashMap<>();
                    bookMap.put("title", bookTitle);
                    bookMap.put("author", authorText);
                    bookMap.put("year", yearText);

                    bFirestore.collection("Books").add(bookMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(NewBooksActivity.this, "Book added!", Toast.LENGTH_SHORT).show();
                            Intent listBookIntent = new Intent(NewBooksActivity.this, MainActivity.class);
                            startActivity(listBookIntent);
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();
                            Toast.makeText(NewBooksActivity.this, "Error : " + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {

                    Toast.makeText(NewBooksActivity.this, "Did you forget anything?", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

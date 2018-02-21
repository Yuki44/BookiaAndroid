package com.example.yuki.bookia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class BookDetailActivity extends AppCompatActivity {

    private Button bDeleteButton;
    private TextView bBookTitleInfo;
    private TextView bBookAuthorInfo;
    private TextView bBookYearInfo;
    private TextView bBookDescriptionInfo;
    private TextView bBookCommentInfo;
    private TextView bBookIsbnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bBookTitleInfo = findViewById(R.id.bookTitleInfo);
        bBookAuthorInfo = findViewById(R.id.bookAuthorInfo);
        bBookYearInfo = findViewById(R.id.bookYearInfo);
        bBookDescriptionInfo = findViewById(R.id.bookDescriptionInfo);
        bBookCommentInfo = findViewById(R.id.bookCommentInfo);
        bBookIsbnInfo = findViewById(R.id.bookIsbnInfo);
        bDeleteButton = findViewById(R.id.deleteBtn);
    }
}

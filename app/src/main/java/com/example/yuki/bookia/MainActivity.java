package com.example.yuki.bookia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "FireLog";
    private RecyclerView bBookList;
    private FirebaseFirestore bFirestore;
    private BooksListAdapter booksListAdapter;
    private List<Books> booksList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        booksList = new ArrayList<>();
        booksListAdapter = new BooksListAdapter(getApplicationContext(), booksList);

        bBookList = findViewById(R.id.book_list);
        bBookList.setHasFixedSize(true);
        bBookList.setLayoutManager(new LinearLayoutManager(this));
        bBookList.setAdapter(booksListAdapter);


        bFirestore = FirebaseFirestore.getInstance();

        bFirestore.collection("Books").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    Log.d(TAG, "Error : " + e.getMessage());
                }
                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        String book_id = doc.getDocument().getId();

                        Books books = doc.getDocument().toObject(Books.class).withId(book_id);
                        booksList.add(books);

                        booksListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addBookIntent = new Intent(MainActivity.this, NewBooksActivity.class);
                startActivity(addBookIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    public void runInfoActivity(BooksListAdapter.ViewHolder view)
//    {
//        Intent bookInfoIntent = new Intent(MainActivity.this, BookDetailActivity.class);
//        startActivity(bookInfoIntent);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.yuki.bookia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by yuki on 20/02/2018.
 */

public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.ViewHolder> {

    public List<Books> booksList;
    public Context context;

    public BooksListAdapter(Context context, List<Books> booksList) {
        this.booksList = booksList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleText.setText(booksList.get(position).getTitle());
        holder.authorText.setText(booksList.get(position).getAuthor());

        final String book_id = booksList.get(position).bookId;

        holder.bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO
                Toast.makeText(context, "Book ID : " + book_id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleText;
        public TextView authorText;
        View bView;

        public ViewHolder(View itemView) {
            super(itemView);
            bView = itemView;

            titleText = bView.findViewById(R.id.title_text);
            authorText = bView.findViewById(R.id.author_text);

        }

    }
}

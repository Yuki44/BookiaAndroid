package com.example.yuki.bookia;

/**
 * Created by yuki on 20/02/2018.
 */

public class Books extends BookId {

    String title, author;

    public Books() {

    }

    public Books(String title, String author) {
        this.title = title;
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

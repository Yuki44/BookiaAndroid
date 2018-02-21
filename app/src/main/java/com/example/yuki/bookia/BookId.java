package com.example.yuki.bookia;

import android.support.annotation.NonNull;

/**
 * Created by yuki on 21/02/2018.
 */

public class BookId {

    public String bookId;

    public <T extends BookId> T withId(@NonNull final String id) {
        this.bookId = id;
        return (T) this;
    }
}

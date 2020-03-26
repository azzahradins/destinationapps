package com.example.destinationapps;

import com.example.destinationapps.models.Author;

public class Application extends android.app.Application {
    private static Author author;
    //TODO 1 : CREATE SESSION MODELS.
    // TODO 2 : CREATE LOGIN ACTIVITY.
    @Override
    public void onCreate() {
        super.onCreate();

        author = new Author("Shafira");
    }

    public static Author getAuthor(){return author;}
}

package com.example.destinationapps;

import com.example.destinationapps.models.Author;
import com.example.destinationapps.models.Session;

public class Application extends android.app.Application {
    private static Author author;
    private static Session session;

    @Override
    public void onCreate() {
        super.onCreate();

        author = new Author("Shafira", "1234");
        session = new Session(this);
    }

    public static Author getAuthor(){return author;}
    public static Session getSession(){return session;}
}

package com.example.destinationapps.models;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.example.destinationapps.Application;

public class Session {
    public static final String USERNAME_KEY = "key_user";
    public static final String TOKEN_KEY = "key_token";
    public static final String FIRST_KEY = "key_first";
    public static final String KEEP_KEY = "key_keep";
    private SharedPreferences preferences;
    Author author;

    public Session(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        author = Application.getAuthor();
    }

    public String getUsername() {
        return preferences.getString(USERNAME_KEY, null);
    }

    public void setUsername(String username) {
        preferences.edit().putString(USERNAME_KEY, username)
                .apply();
    }

    public boolean getKeep(){return preferences.getBoolean(KEEP_KEY, true);}

    public void setSession(String token, String username, boolean keep) {
        preferences.edit().putString(TOKEN_KEY, token)
                .apply();
        preferences.edit().putString(USERNAME_KEY, username)
                .apply();
        preferences.edit().putBoolean(KEEP_KEY, keep)
                .apply();
    }

    public boolean isLoggedIn() {
        String token = preferences.getString(TOKEN_KEY, null);
        return (token != null);
    }

    public boolean validate(String username, String password, boolean keep) {
        if (username.equals(author.getName()) && password.equals(author.getPass())) {
            setSession(username, username, keep);
            return true;
        }
        return false;
    }

    public void logout() {
        preferences.edit().remove(TOKEN_KEY)
                .apply();
    }

    public boolean isFirstTime() {
        return preferences.getBoolean(FIRST_KEY, true);
        //IF isFirstTime?
        //RETURN TRUE > THEN WE SHOW HELP ACTIVITY
    }

    public void setFirstTime(boolean firstTime) {
        preferences.edit().putBoolean(FIRST_KEY, firstTime)
                .apply();
    }
}

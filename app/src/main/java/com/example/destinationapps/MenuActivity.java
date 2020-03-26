package com.example.destinationapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    //TODO 5 : FIX YOUR LOGIN/LOGOUT TEXT WHILE THERE IS SESSION.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void GoToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

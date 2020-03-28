package com.example.destinationapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.destinationapps.models.Session;

public class AccountActivity extends AppCompatActivity {
    Session session = Application.getSession();
    TextView textName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        textName = findViewById(R.id.text_username);
        textName.setText(session.getUsername());
    }

    public void Logout(View view) {
        session.logout();
        finish();
    }
}

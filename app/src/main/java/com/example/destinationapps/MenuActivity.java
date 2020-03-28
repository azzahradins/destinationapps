package com.example.destinationapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.destinationapps.models.Session;

public class MenuActivity extends AppCompatActivity {
    Session session;
    TextView textLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        textLogin = findViewById(R.id.account_info);
        session = Application.getSession();
        checkSession();
    }

    private void checkSession() {
        if(session.isLoggedIn()){
            textLogin.setText("Your Account");
        }else{
            textLogin.setText("Login");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkSession();
    }

    public void GoToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void GoToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("login", 0);
        startActivity(intent);
    }
}

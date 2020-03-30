package com.example.destinationapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.destinationapps.models.Session;

public class SplashActivity extends AppCompatActivity {
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        hideAppBar();
        session = Application.getSession();
        if(!session.isKeepLogin()){
            session.logout();
        }
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(session.isFirstTime()){
                    Intent mainIntent = new Intent(SplashActivity.this,  HelpActivity.class);
                    startActivity(mainIntent);
                    finish();
                }else{
                    Intent mainIntent = new Intent(SplashActivity.this,  MenuActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, 2000);
    }

    private void hideAppBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}

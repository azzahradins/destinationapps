package com.example.destinationapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        hideAppBar();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent mainIntent = new Intent(SplashActivity.this,  MenuActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 2000);
    }

    private void hideAppBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}

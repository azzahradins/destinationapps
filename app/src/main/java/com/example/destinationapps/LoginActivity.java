package com.example.destinationapps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.destinationapps.models.Session;

public class LoginActivity extends AppCompatActivity {

    Session session;
    EditText username, password;
    boolean keep = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.input_username);
        password = findViewById(R.id.input_password);
        session = Application.getSession();
        if(session.isLoggedIn()){
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void submitLogin(View view) {
        if(session.validate(username.getText().toString(), password.getText().toString(), keep)){
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                int insert = extras.getInt("login");
                if(insert == 1){
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Intent intent = new Intent(this, AccountActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }else{
            loginFailed();
        }
    }
    public void loginFailed(){
        AlertDialog optionDialog = new AlertDialog.Builder(this).create();
        optionDialog.setMessage("Username/Password incorrect");
        optionDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        optionDialog.show();
    }

    public void KeepLogin(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkbox_keep:
                if (checked) keep = true;
                else keep = false;
                break;
        }
        }
}

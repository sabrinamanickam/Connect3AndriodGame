package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    public static final String extra_player1="player1";
    public static final String extra_player2="player2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton= (Button) findViewById(R.id.LoginButton) ;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }
    public void openMainActivity()
    {

        EditText editText=(EditText) findViewById(R.id.editText);
        String player1= editText.getText().toString();

        EditText editText2=(EditText) findViewById(R.id.editText2);
        String player2= editText2.getText().toString();

        Intent in=new Intent(this,MainActivity.class);
        in.putExtra(extra_player1,player1);
        in.putExtra(extra_player2,player2);

        startActivity(in);
    }

}

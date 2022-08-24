package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class savedData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data);

        TextView textView5=(TextView)findViewById(R.id.textView5);

        SharedPreferences sp= getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String winner=sp.getString("winner","");

        textView5.setText(winner);

    }
}

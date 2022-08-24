package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EndGame extends AppCompatActivity {
    TextView nm_textView;
    Button Button_add,Button_viewAll;
    ListView lv_winnerView;
    ArrayAdapter winnerArray;
    DataBaseHelper dataBaseHelper;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
       Intent intent1=getIntent();
       final TextView textView4=(TextView) findViewById(R.id.nm_textView);
        final String getRes=intent1.getStringExtra(MainActivity.str1);


        textView4.setText(getRes);




        nm_textView=findViewById(R.id.nm_textView);
        Button_add=(Button)findViewById(R.id.Button_add);
        Button_viewAll=(Button)findViewById(R.id.Button_viewAll);
        lv_winnerView=findViewById(R.id.lv_winnerView);
        dataBaseHelper=new DataBaseHelper(EndGame.this);

        showWinnerOnLIstView(dataBaseHelper);
        Button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WinnerModel winnerModel;
                try {

                    winnerModel = new WinnerModel(nm_textView.getText().toString());
                    Toast.makeText(EndGame.this, winnerModel.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(EndGame.this, "Error", Toast.LENGTH_SHORT).show();
                    winnerModel=new WinnerModel("error");

                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(EndGame.this);

                boolean success = dataBaseHelper.addOne(winnerModel);
                //Toast.makeText(EndGame.this, "success="+success, Toast.LENGTH_SHORT).show();
                showWinnerOnLIstView(dataBaseHelper);
            }
        });

        Button_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper=new DataBaseHelper(EndGame.this);

                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
                showWinnerOnLIstView(dataBaseHelper);

            }
        });
        lv_winnerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                WinnerModel clickedWinner=(WinnerModel)parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedWinner);
                showWinnerOnLIstView(dataBaseHelper);
                Toast.makeText(EndGame.this,"Deleted"+clickedWinner.toString(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        playAgain=findViewById(R.id.playAgain);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack=new Intent(EndGame.this,Login.class);
                startActivity(goBack);
            }
        });

    }


    private void showWinnerOnLIstView(DataBaseHelper dataBaseHelper2) {
        winnerArray = new ArrayAdapter<WinnerModel>(EndGame.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getAll());
        lv_winnerView.setAdapter(winnerArray);

    }


    }



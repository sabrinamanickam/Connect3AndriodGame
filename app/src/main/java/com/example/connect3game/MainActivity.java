package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //yellow=0, red=1
    int activegamer = 0,player1points=0,player2points=0,flag=0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningpositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive=true;
    String player1,player2,msg="";
    TextView textView;
    TextView textView2;
    TextView WinnerTextView;
    Button PlayAgainButton;
    ImageView RedImageView,YellowImageView;
    public static final String str1="points";

    public void dropin(View view)
    {


        ImageView counter = (ImageView) view;

        int tappedView = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedView]==2 &&gameActive)
        {
            gameState[tappedView] = activegamer;
            counter.setTranslationY(-1500);
            if (activegamer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activegamer = 1;
                RedImageView.setBackgroundColor(Color.WHITE);
                YellowImageView.setBackgroundColor(Color.BLUE);

            } else {

                counter.setImageResource(R.drawable.red);
                activegamer = 0;
                YellowImageView.setBackgroundColor(Color.WHITE);
                RedImageView.setBackgroundColor(Color.BLUE);
            }
            counter.animate().translationYBy(1500).setDuration(100);
            for (int[] winningpos : winningpositions) {
                if (gameState[winningpos[0]] == gameState[winningpos[1]] && gameState[winningpos[1]] == gameState[winningpos[2]] && gameState[winningpos[0]] != 2) {
                    gameActive = false;
                    if (activegamer == 1) {
                        // msg = "  "+ player2+" has won!!";

                        flag = 1;
                        player2wins();
                    } else if (activegamer == 0) {
                        // msg = "  "+player1+" has
                        flag = 1;
                        player1wins();
                    }



                    WinnerTextView=(TextView)findViewById(R.id.WinnerTextView);
                    PlayAgainButton=(Button)findViewById(R.id.PlayAgainButton);
                    WinnerTextView.setText(msg);
                    WinnerTextView.setVisibility(View.VISIBLE);
                    PlayAgainButton.setVisibility(View.VISIBLE);
                }



            }

        }



    }





    public void playAgain(View view)
    {
        flag=0;
        TextView WinnerTextView=(TextView)findViewById(R.id.WinnerTextView);
        Button PlayAgainButton=(Button)findViewById(R.id.PlayAgainButton);
        WinnerTextView.setVisibility(View.INVISIBLE);
        PlayAgainButton.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView image = (ImageView)gridLayout.getChildAt(i);
            image.setImageDrawable(null);
        }
        activegamer = 0;
        int i;
        for(i=0;i<=8;i++)
        {
            gameState[i]=2;
        }
        gameActive=true;

    }
    public void endGame(View view)
    {
       Button endButton=(Button) findViewById(R.id.endButton);
       endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,EndGame.class);
                if(player1points>player2points)
                   intent1.putExtra(str1,player2+" WON!!");
               else if(player1points<player2points)
                  intent1.putExtra(str1,player1+"  WON!!");
               else
                    intent1.putExtra(str1,"ITS A DRAW!!");

                startActivity(intent1);
            }
        });
    }
    public void player2wins(){
        msg = "  "+ player2+" has won!!";
        player1points++;
        updatePointsBoard();

    }
    public void player1wins(){
        msg = "  "+ player1+" has won!!";
        player2points++;
        updatePointsBoard();
    }

    public void draw()
    {
        msg="Its a draw";
        WinnerTextView.setVisibility(View.VISIBLE);
        PlayAgainButton.setVisibility(View.VISIBLE);

    }
    public void updatePointsBoard()
    {


        textView.setText(player1+": " +player2points);
        textView2.setText(player2+": "+player1points);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Intent in=getIntent();
        player1=in.getStringExtra(Login.extra_player1);
        player2=in.getStringExtra(Login.extra_player2);

        textView=(TextView)  findViewById(R.id.textView);
        textView2=(TextView)  findViewById(R.id.textView2);

        textView.setText(player1+":  0");
        textView2.setText(player2+": 0 ");
        RedImageView =findViewById(R.id.RedImageView);
        YellowImageView=findViewById(R.id.YellowImageView);
    }

}

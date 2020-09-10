package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    boolean tabIsFull = true;
    Button playAgainButton;
    TextView winnerTextView;

    @SuppressLint("SetTextI18n")
    public void dropIn(View view){
        tabIsFull = true;
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.ographic2);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.xgraphic);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner;
                    gameActive = false;
                    if (activePlayer == 0)
                        winner = "Krzyżyk";
                    else
                        winner = "Kółko";

                    winnerTextView.setText(winner + " wygrywa!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
            for(int gameStates : gameState){
                if (gameStates == 2) {
                    tabIsFull = false;
                    break;
                }
            }
            if(tabIsFull && gameActive){
                winnerTextView.setText("Remis");
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
                gameActive = false;
            }
        }

    }

    public void playAgain(View view){
        Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        for(int i = 0; i<constraintLayout.getChildCount();i++)
        {
            ImageView counter = (ImageView) constraintLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i = 0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
         gameActive = true;
         activePlayer = 0;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgainButton = findViewById(R.id.playAgainButton);
        winnerTextView = findViewById(R.id.winnerTextView);
    }
}

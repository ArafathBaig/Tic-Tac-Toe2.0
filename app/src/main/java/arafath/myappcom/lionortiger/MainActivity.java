package arafath.myappcom.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private enum Player{
        ONE,TWO,NO
    }

    private Player currentPlayer = Player.ONE;
    private Player[] playerChoices= new Player[9];
    private boolean gameOver = false;
    private Button playAgain;
    private GridLayout  gridLay;
    private TextView heading ;
    private Button start;
    private TextView credits;
    private TextView score;
    int count = 0;
    int count1 = 0;
    int count2=0;





    int[][] winningPossibilities = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0 ; i< 9 ; i++){
            playerChoices[i] = Player.NO;
        }

        score = findViewById(R.id.score);
        credits = findViewById(R.id.textView2);
        start = findViewById(R.id.start);
        heading = findViewById(R.id.textView);
        gridLay = findViewById(R.id.gridLayout);
        playAgain = findViewById(R.id.button);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridLay.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                credits.setVisibility(View.GONE);
                score.setVisibility(View.VISIBLE);
            }
        });

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resetTheGame();

            }
        });
    }
    public void imageViewIsTapped(View imageView) {

        ImageView tappedImageView = (ImageView) imageView;
        int tictag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[tictag] == Player.NO && !gameOver) {
            tappedImageView.setTranslationX(-2000);
            count++;
            playerChoices[tictag] = currentPlayer;
            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }

            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(1800).setDuration(1000);

            for (int[] winnerColumns : winningPossibilities) {

                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] &&
                        playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] &&
                        playerChoices[winnerColumns[0]] != Player.NO) {
                    String winner = "";
                    playAgain.setVisibility(View.VISIBLE);
                    gameOver = true;
                    if (currentPlayer == Player.TWO) {
                        winner = "Player One";
                        count1++;
                    } else if (currentPlayer == Player.ONE) {
                        winner = "Player Two";
                        count2++;
                    }
                    count=0;
                    Toast.makeText(this, winner + " is the winner", Toast.LENGTH_LONG).show();
                    score.setText(count1+"  -  "+count2);


                }
            }

        }

        if(count == 9 && !gameOver){
            Toast.makeText(this, "Draw", Toast.LENGTH_LONG).show();
            playAgain.setVisibility(View.VISIBLE);
            gameOver = true;
            count = 0;
        }


    }

    public void resetTheGame(){

        for(int i = 0; i < gridLay.getChildCount(); i++){
            ImageView imageView = (ImageView) gridLay.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }
        currentPlayer = Player.ONE;

        for(int i = 0 ; i< 9 ; i++){
            playerChoices[i] = Player.NO;
        }

        gameOver = false;
        playAgain.setVisibility(View.GONE);



    }


}

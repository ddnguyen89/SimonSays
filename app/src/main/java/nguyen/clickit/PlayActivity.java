package nguyen.clickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class PlayActivity extends AppCompatActivity implements OnClickListener {

    //define widget variables
    private TextView scoreTV, timeTV;
    private Button greenButton, redButton, blueButton, yellowButton;
    private ImageButton quitButton;

    private int simon;
    private int user;

    private int score = 0;
    private int highscore;

    CountDownTimer timer;

    private Handler setDelay = new Handler();
    private Runnable startDelay;

    private SharedPreferences savedScores;
    Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        timeTV = (TextView) findViewById(R.id.timeTV);

        greenButton = (Button) findViewById(R.id.greenButton);
        redButton = (Button) findViewById(R.id.redButton);
        blueButton = (Button) findViewById(R.id.blueButton);
        yellowButton = (Button) findViewById(R.id.yellowButton);

        quitButton = (ImageButton) findViewById(R.id.quitButton);

        //setting onClickListener for buttons
        greenButton.setOnClickListener(this);
        redButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);
        yellowButton.setOnClickListener(this);

        quitButton.setOnClickListener(this);

        playGame();

        savedScores = getSharedPreferences("savedScores", MODE_PRIVATE);
        editor = savedScores.edit();
        editor.putInt("userScore", 0).commit();

        highscore = savedScores.getInt("highScore", 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.greenButton:
                user = 1;
                checked();
                break;
            case R.id.redButton:
                user = 2;
                checked();
                break;
            case R.id.blueButton:
                user = 3;
                checked();
                break;
            case R.id.yellowButton:
                user = 4;
                checked();
                break;

            case R.id.quitButton:
                timer.cancel();
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                break;
        }
    }

    //function to match user action with simon
    public boolean checkMatch(int simon, int user) {
        boolean matchStatement;

        if (simon == user) {
            matchStatement = true;
            score += 1;
            scoreTV.setText(""+score);

            editor = savedScores.edit();
            editor.putInt("userScore", score);
            editor.putInt("highScore", highscore);

            if(score > highscore) {
                highscore = score;
                Log.d("userScore", ""+score);
                Log.d("Highscore", ""+highscore);
                editor.putInt("highScore", score);
            }
            editor.commit();

        } else {
            matchStatement = false;
        }

        return matchStatement;
    }

    //function to create and add random number from 1-4 to simon
    public void randomNumber() {
        final int min = 1;
        final int max = 4;

        int randomNumber = new Random().nextInt((max - min) + 1) + min;

        while (randomNumber == simon) {
            randomNumber = new Random().nextInt((max - min) + 1) + min;
        }

        simon = randomNumber;
    }

    public void playGame() {
        randomNumber();
        simonFlash(simon);
        timer = new CountDownTimer(10000,100) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeTV.setText(""+millisUntilFinished/100);
            }

            @Override
            public void onFinish() {
                editor = savedScores.edit();
                editor.putInt("userScore", score);
                editor.putInt("highScore", highscore);

                if(score > highscore) {
                    highscore = score;
                    Log.d("userScore", ""+score);
                    Log.d("Highscore", ""+highscore);
                    editor.putInt("highScore", score);
                }

                editor.commit();

                if(isFinishing())
                    timer.cancel();

                Intent highscoreIntent = new Intent(getApplicationContext(), HighscoreActivity.class);
                startActivity(highscoreIntent);
            }
        }.start();
    }

    public void checked() {
        if (checkMatch(simon, user) == true) {
            randomNumber();
            simonFlash(simon);
        } else {
            timer.cancel();

            Intent highscoreIntent = new Intent(this, HighscoreActivity.class);
            startActivity(highscoreIntent);
        }
    }

    public void simonFlash(int simon) {
        if (simon == 1) {
            greenButton.setPressed(true);
        } else if (simon == 2) {
            redButton.setPressed(true);
        } else if (simon == 3) {
            blueButton.setPressed(true);
        } else if (simon == 4) {
            yellowButton.setPressed(true);
        }
    }
}


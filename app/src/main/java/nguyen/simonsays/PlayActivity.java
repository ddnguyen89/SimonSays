package nguyen.simonsays;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

public class PlayActivity extends AppCompatActivity implements OnClickListener{

    //define widget variables
    private TextView scoreTV;
    private Button greenButton, redButton, blueButton, yellowButton;
    private ImageButton quitButton;

    private List<Integer> simonSays = new ArrayList<Integer>();
    private List<Integer> userSays = new ArrayList<Integer>();

    private final int FLASH_DURATION = 1200;

    private Handler setDelay = new Handler();
    private Runnable startDelay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        scoreTV = (TextView) findViewById(R.id.scoreTV);

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



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.greenButton:
                userSays.add(1);
                checked();


                Log.d("green", userSays.toString());
                Log.d("simon", simonSays.toString());
                break;
            case R.id.redButton:
                userSays.add(2);
                checked();


                Log.d("red", userSays.toString());
                Log.d("simon", simonSays.toString());
                break;
            case R.id.blueButton:
                userSays.add(3);
                checked();

                Log.d("blue", userSays.toString());
                Log.d("simon", simonSays.toString());
                break;
            case R.id.yellowButton:
                userSays.add(4);
                checked();

                Log.d("yellow", userSays.toString());
                Log.d("simon", simonSays.toString());
                break;
            case R.id.quitButton:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                break;
        }
    }

    //function to match user action with simon
    public boolean checkMatch(List<Integer> simon, List<Integer> user) {
        boolean matchStatement = false;

        for(int i = 0; i < user.size(); i++) {
            if(simon.get(i).equals(user.get(i))) {
                matchStatement = false;
                break;
            } else {
                matchStatement = true;
            }
        }
        return matchStatement;
    }

    //function to create and add random number from 1-4 to simon
    public void addRandomNumber() {
        final int min = 1;
        final int max = 4;

        for(int i = 0; i < 5; i++) {
            int randomNumber = new Random().nextInt((max - min) + 1) + min;


            simonSays.add(randomNumber);
        }
        Log.d("randomNumber", String.valueOf(simonSays));
    }

    public void playGame() {

        addRandomNumber();

        simonFlash(simonSays);

        Log.d("simon size", String.valueOf(simonSays.size()));
        Log.d("user size", String.valueOf(userSays.size()));
    }

    public void checked() {
        if (checkMatch(simonSays, userSays)) {
            Intent highschoolIntent = new Intent(this, HighscoreActivity.class);
            startActivity(highschoolIntent);
            Log.d("not a match", String.valueOf(checkMatch(simonSays, userSays)));
        } else {
            addRandomNumber();

            simonFlash(simonSays);

            Log.d("match", String.valueOf(checkMatch(simonSays, userSays)));
        }
    }

    public int i = 0;

    public void simonFlash(final List<Integer> simon) {

        for(int i = 0; i < simon.size(); i++) {

            boolean pause = false;
            while(!pause){

            if (simonSays.get(i) == 1) {

                greenButton.setPressed(true);
                startDelay = new Runnable() {
                    @Override
                    public void run() {
                        greenButton.setPressed(false);
                    }
                };
                setDelay.postDelayed(startDelay, FLASH_DURATION);

                Log.d("number-green", String.valueOf(simonSays.get(i)));
            }

            else if (simonSays.get(i) == 2) {
                redButton.setPressed(true);

                startDelay = new Runnable() {
                    @Override
                    public void run() {
                        redButton.setPressed(false);
                    }
                };
                setDelay.postDelayed(startDelay, FLASH_DURATION);

                Log.d("number-red", String.valueOf(simonSays.get(i)));
            }

            else if (simonSays.get(i) == 3) {

                blueButton.setPressed(true);

                startDelay = new Runnable() {
                    @Override
                    public void run() {
                        blueButton.setPressed(false);
                    }
                };

                setDelay.postDelayed(startDelay, FLASH_DURATION);

                Log.d("number-blue", String.valueOf(simonSays.get(i)));
            }

            else if (simonSays.get(i) == 4) {

                yellowButton.setPressed(true);

                startDelay = new Runnable() {
                    @Override
                    public void run() {
                        yellowButton.setPressed(false);
                    }
                };

                setDelay.postDelayed(startDelay, FLASH_DURATION);

                Log.d("number-yellow", String.valueOf(simonSays.get(i)));
            }

            if (pause == false){
                startDelay = new Runnable() {
                    @Override
                    public void run() {
                    }
                };
                setDelay.postDelayed(startDelay, 5000);
                pause = true;
            }


        }}
    }
}


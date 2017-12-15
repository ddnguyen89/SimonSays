package nguyen.simonsays;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity implements OnClickListener{

    //define widget variables
    private TextView scoreTV;
    private Button greenButton, redButton, blueButton, yellowButton;
    private ImageButton quitButton;

    private int[] simonSays = new int[25];
    private int[] userSays = new int[25];

    private int simonIndex = 0;

    private final int FLASH_DURATION = 1200;

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

       // playGame();

        addRandomNumber();

        simonSequence(0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.greenButton:
                getButton(1);



                Log.d("green", userSays.toString());
                Log.d("simon", simonSays.toString());
                break;
            case R.id.redButton:
                getButton(2);



                Log.d("red", userSays.toString());
                Log.d("simon", simonSays.toString());
                break;
            case R.id.blueButton:
                getButton(3);


                Log.d("blue", userSays.toString());
                Log.d("simon", simonSays.toString());
                break;
            case R.id.yellowButton:
                getButton(4);

                Log.d("yellow", userSays.toString());
                Log.d("simon", simonSays.toString());
                break;
            case R.id.quitButton:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                break;
        }
    }

    private void getButton(int button) {
        for(int i = 0; i < simonIndex; i++) {
            if(simonSays[simonIndex] == button) {
                simonIndex++;
            } else {
                Handler handler= new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                    }
                }, 1000);
                break;
            }
        }
        
        simonSequence(simonIndex);
    }

    private void simonSequence(int simonIndex) {
        for(int i = 0; i <= simonIndex; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            simonKey(simonSays[i]);
        }

    }

    private void simonKey(int simonSay) {
        if(simonSay == 1) {
            greenButton.setPressed(true);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    greenButton.setPressed(false);
                }
            }, FLASH_DURATION);
        }

        else if(simonSay == 2) {
            redButton.setPressed(true);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    redButton.setPressed(false);
                }
            }, FLASH_DURATION);
        }

        else if(simonSay == 3) {
            blueButton.setPressed(true);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    blueButton.setPressed(false);
                }
            }, FLASH_DURATION);
        }

        else if(simonSay == 4) {
            yellowButton.setPressed(true);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    yellowButton.setPressed(false);
                }
            }, FLASH_DURATION);
        }
    }


    //function to match user action with simonsays
   /* public boolean checkMatch(int[] simon, List<Integer> user) {
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
    }*/

    //function to create and add random number from 1-4 to simon
    public void addRandomNumber() {
        final int min = 1;
        final int max = 4;


        for(int i = 0; i < 25; i++){
            int randomNumber = new Random().nextInt((max - min) + 1) + min;
            simonSays[i] = (randomNumber);
        }
        Log.d("randomNumber", String.valueOf(simonSays));
    }

    public void playGame() {

        addRandomNumber();

       // simonFlash();

      ///  Log.d("simon size", String.valueOf(simonSays.size()));
      //  Log.d("user size", String.valueOf(userSays.size()));
    }
/*
    public void checked() {
        if (checkMatch(simonSays, userSays)) {
            Intent highschoolIntent = new Intent(this, HighscoreActivity.class);
            startActivity(highschoolIntent);
            Log.d("not a match", String.valueOf(checkMatch(simonSays, userSays)));
        } else {

            simonFlash();

            Log.d("match", String.valueOf(checkMatch(simonSays, userSays)));
        }
    }

    public void simonFlash() {

        for(int i = 0; i < simonSays.size(); i++) {
            if(simonSays.get(i).equals(1)) {
                greenButton.setPressed(true);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        greenButton.setPressed(false);
                    }
                };
                time.schedule(task, 0, 1000);
            }

            else if(simonSays.get(i).equals(2)) {
                redButton.setPressed(true);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        redButton.setPressed(false);
                    }
                };
                time.schedule(task, 0, 1000);
            }

            else if(simonSays.get(i).equals(3)) {
                blueButton.setPressed(true);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        blueButton.setPressed(false);
                    }
                };
                time.schedule(task, 0, 1000);
            }

            else if(simonSays.get(i).equals(4)) {
                yellowButton.setPressed(true);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        yellowButton.setPressed(false);
                    }
                };
                time.schedule(task, 0, 1000);
            }

        }
    }*/
}

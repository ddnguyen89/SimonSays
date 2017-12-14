package nguyen.simonsays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class PlayActivity extends AppCompatActivity implements OnClickListener{

    //define widget variables
    private TextView scoreTV;
    private Button greenButton, redButton, blueButton, yellowButton;
    private ImageButton quitButton;

    private String[] simonSays;
    private String[] userSays;


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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.greenButton:
                break;
            case R.id.redButton:
                break;
            case R.id.blueButton:
                break;
            case R.id.yellowButton:
                break;
            case R.id.quitButton:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                break;
        }
    }


    //function to create a random number from 1-4
    public int getRandomNum() {
        final int min = 1;
        final int max = 4;
        int randomButton = new Random().nextInt((min - max) + 1) + min;

        return randomButton;
    }
}

package nguyen.simonsays;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity implements OnClickListener {

    //define widget variables
    private TextView yourScoreTV, highscoreTV;
    private ImageButton playAgainButton, quitButton, resetScoreButton;

    private int userScore;
    private int highScore;

    SharedPreferences savedScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        yourScoreTV = (TextView) findViewById(R.id.scoreTV);
        highscoreTV = (TextView) findViewById(R.id.highscoreTV);

        playAgainButton = (ImageButton) findViewById(R.id.playAgainButton);
        quitButton = (ImageButton) findViewById(R.id.quitButton);
        resetScoreButton = (ImageButton) findViewById(R.id.resetScoreButton);

        //setting onClickListener for buttons
        playAgainButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);
        resetScoreButton.setOnClickListener(this);

        savedScores = getSharedPreferences("savedScores", MODE_PRIVATE);

        userScore = savedScores.getInt("userScore", 0);
        highScore = savedScores.getInt("highScore", 0);

        yourScoreTV.setText(""+userScore);
        highscoreTV.setText(""+highScore);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playAgainButton:
                Intent playIntent = new Intent(this, PlayActivity.class);
                startActivity(playIntent);
                break;
            case R.id.quitButton:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                break;
            case R.id.resetScoreButton:

                break;
        }

    }
}

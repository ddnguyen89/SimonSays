package nguyen.simonsays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    //define widget variables
    private ImageButton playButton, highscoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (ImageButton) findViewById(R.id.playButton);
        highscoreButton = (ImageButton) findViewById(R.id.highscoreButton);

        //setting onClickListener for buttons
        playButton.setOnClickListener(this);
        highscoreButton.setOnClickListener(this);
    }

    //onClick method to display new intent
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                Intent playIntent = new Intent(this, PlayActivity.class);
                startActivity(playIntent);
                break;
            case R.id.highscoreButton:
                Intent highscoreIntent = new Intent(this, HighscoreActivity.class);
                startActivity(highscoreIntent);
                break;
        }
    }
}

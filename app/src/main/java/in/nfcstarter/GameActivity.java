package in.nfcstarter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private ImageView gameImage;
    private TextView gameHint1;
    private TextView gameHint2;
    private TextView gameHint3;
    private TextView gameLevel;
    private TextView gameScore;

    private Thread gameThread;
    private Runnable gameRunnable;
    private int USER_SCORE  = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameImage = (ImageView) findViewById(R.id.gameImage);
        gameHint1 = (TextView) findViewById(R.id.gameHint1);
        gameHint2 = (TextView) findViewById(R.id.gameHint2);
        gameHint3 = (TextView) findViewById(R.id.gameHint3);
        gameLevel = (TextView) findViewById(R.id.gameLevel);
        gameScore = (TextView) findViewById(R.id.gameScore);

        gameRunnable = new Runnable() {
            @Override
            public void run() {
                changeLevel();
            }
        };

        gameThread = new Thread(gameRunnable);

    }

    void changeToLevelOne(){
        gameImage.setImageDrawable(getResources().getDrawable());
        gameScore.setText(USER_SCORE);
        gameLevel.setText("Level : 1");
        gameHint1.setText("This is hint one..");
        gameHint1.setText("This is hint two");
        gameHint3.setText("This is hint three");
    }


    void changeToLevelTwo(){

        gameImage.setImageDrawable(getResources().getDrawable());
        gameScore.setText(USER_SCORE);
        gameLevel.setText("Level : 2");
        gameHint1.setText("This is hint one..");
        gameHint1.setText("This is hint two");
        gameHint3.setText("This is hint three");
    }

    void changeToLevelThree(){

        gameImage.setImageDrawable(getResources().getDrawable());
        gameScore.setText(USER_SCORE);
        gameLevel.setText("Level : 3");
        gameHint1.setText("This is hint one..");
        gameHint1.setText("This is hint two");
        gameHint3.setText("This is hint three");
    }
}

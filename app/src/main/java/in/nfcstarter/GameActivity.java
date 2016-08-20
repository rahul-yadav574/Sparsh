package in.nfcstarter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

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
    private int TRY = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().setTitle("Play Game");

        gameImage = (ImageView) findViewById(R.id.gameImage);
        gameHint1 = (TextView) findViewById(R.id.gameHint1);
        gameHint2 = (TextView) findViewById(R.id.gameHint2);
        gameHint3 = (TextView) findViewById(R.id.gameHint3);
        gameLevel = (TextView) findViewById(R.id.gameLevel);
        gameScore = (TextView) findViewById(R.id.gameScore);

        changeToLevelOne();


        final Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (TRY ==0){
                            TRY =1 ;
                            USER_SCORE = 10;
                            changeToLevelTwo();
                        }
                        else if (TRY == 1){

                            new AlertDialog.Builder(GameActivity.this)
                                    .setCancelable(false)
                                    .setTitle("Game Over...")
                                    .setMessage("You Scored 10 points..")
                                    .setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            USER_SCORE =0;
                                            dialog.cancel();
                                            changeToLevelOne();
                                        }
                                    })
                            .show();

                            timer.cancel();
                        }
                    }
                });

            }
        }, 5000, 5000);

    }

    void changeToLevelOne(){
        gameImage.setImageDrawable(getResources().getDrawable(R.drawable.level_1));
        gameScore.setText(String.valueOf(USER_SCORE));
        gameLevel.setText("Level : 1");
        gameHint1.setText("This is hint one..");
        gameHint2.setText("This is hint two");
        gameHint3.setText("This is hint three");
    }


    void changeToLevelTwo(){

        gameImage.setImageDrawable(getResources().getDrawable(R.drawable.level_2));
        gameScore.setText(String.valueOf(USER_SCORE));
        gameLevel.setText("Level : 2");
        gameHint1.setText("This is hint one..");
        gameHint2.setText("This is hint two");
        gameHint3.setText("This is hint three");
    }

}

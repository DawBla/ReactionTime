package com.example.reactiontime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import org.apache.commons.lang3.time.StopWatch;

public class SoloActivity extends AppCompatActivity {
    //Monitor the state of the game
    //1 - Default, wait for the start
    //2 - Game on, wait for user input
    //3 - Game over, result screen
    int gameState;
    int delay;
    StopWatch stopwatch = new StopWatch();
    //Initialize handler and runnable for the delayed green screen
    Handler delayHandler = new Handler();


    //Fire when user touches anywhere on the screen
    @Override
    public void onUserInteraction() {
        //Set up references
        TextView textSolo = findViewById(R.id.textSolo);
        ConstraintLayout layout = findViewById(R.id.layout_solo);

        super.onUserInteraction();
        switch (gameState){
            //Handle the setup
            case 1:
                textSolo.setText(R.string.tap_to_start);
                textSolo.setTextColor(getResources().getColor(R.color.text_neutral_light));
                layout.setBackgroundColor(getResources().getColor(R.color.bg_neutral_light));
                gameState = 2;
                break;
            //Handle the game
            case 2:
                textSolo.setText(R.string.tap_when_green);
                textSolo.setTextColor(getResources().getColor(R.color.text_neutral_light));
                //Randomizing the delay
                Random rand = new Random();
                delay = rand.nextInt(5000);
                delay += 1000;
                //Change screen color after random delay
                Runnable delayedRunnable = new Runnable() {
                    @Override
                    public void run() {
                        layout.setBackgroundColor(getResources().getColor(R.color.bg_tap_now));
                        textSolo.setText(R.string.tap_now);
                        stopwatch.start();
                    }
                };
                delayHandler.postDelayed(delayedRunnable, delay);
                gameState = 3;
                break;
            //Handle result
            case 3:
                if(stopwatch.isStarted()){
                    stopwatch.stop();
                    String score = getString(R.string.success_1)+stopwatch.getTime()+getString(R.string.success_2);
                    textSolo.setText(score);
                    textSolo.setTextColor(getResources().getColor(R.color.text_success));
                    layout.setBackgroundColor(getResources().getColor(R.color.bg_success));
                    stopwatch.reset();
                }
                else{
                    delayHandler.removeCallbacksAndMessages(null);
                    textSolo.setText(R.string.fail_too_soon);
                    textSolo.setTextColor(getResources().getColor(R.color.text_fail));
                    layout.setBackgroundColor(getResources().getColor(R.color.bg_fail));
                }
                gameState = 1;
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);

        TextView textSolo = findViewById(R.id.textSolo);
        ConstraintLayout layout = findViewById(R.id.layout_solo);
        textSolo.setText(R.string.tap_to_start);
        textSolo.setTextColor(getResources().getColor(R.color.text_neutral_light));
        layout.setBackgroundColor(getResources().getColor(R.color.bg_neutral_light));
        gameState = 2;
    }

}
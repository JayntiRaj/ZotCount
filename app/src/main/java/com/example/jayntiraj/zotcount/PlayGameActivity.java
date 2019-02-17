package com.example.jayntiraj.zotcount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;

public class PlayGameActivity extends AppCompatActivity {
    String player_name = "";
    public static final String PLAYER_NAME_MESSAGE = "com.example.zotcount.MESSAGE";
    public static final String PLAYER_SCORE_MESSAGE = "com.example.zotcount.MESSAGE2";


    CountDownTimer timer;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        Intent incomingIntent = getIntent();
        player_name = incomingIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                textView = findViewById(R.id.timer);
                textView.setText("Time left:  " + Long.toString(millisUntilFinished / 1000) + "  seconds");
            }

            @Override
            public void onFinish() {
                textView = findViewById(R.id.timer);
                textView.setText("Time's Up!");
                endGame();
            }
        }.start();

    }

    public void endGame() {

        Intent outgoingIntent = new Intent(this, ScorePageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String player_text = editText.getText().toString();
        int player_score = getScore(player_text);
        //String player_score_string = Integer.toString(player_score);

        Bundle extras = new Bundle();

        extras.putString(PLAYER_NAME_MESSAGE, player_name);
        extras.putInt(PLAYER_SCORE_MESSAGE, player_score);

        //outgoingIntent.putExtra(PLAYER_NAME_MESSAGE, player_name);
        //outgoingIntent.putExtra(PLAYER_SCORE_MESSAGE, player_score_string);

        outgoingIntent.putExtras(extras);

        startActivity(outgoingIntent);

    }

    int getScore(String s){
        int score = 0;
        char z = 'z';
        char o = 'o';
        char t = 't';
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == z)
            {
                if(i+1 < s.length() && s.charAt(i+1) == o)
                {
                    if(i+2 <s.length() && s.charAt(i+2) == t)
                    {
                        score++;
                    }
                }
            }
        }
        return score;
    }
}

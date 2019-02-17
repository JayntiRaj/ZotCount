package com.example.jayntiraj.zotcount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScorePageActivity extends AppCompatActivity {

    public static final String PLAYER_NAME_MESSAGE = "com.example.zotcount.MESSAGE";

    String player_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        Intent incomingIntent = getIntent();

        Bundle extras = incomingIntent.getExtras();

        int player_score = extras.getInt(PlayGameActivity.PLAYER_SCORE_MESSAGE);

        player_name = extras.getString(PlayGameActivity.PLAYER_NAME_MESSAGE);

        TextView textView = findViewById(R.id.finalScore);
        textView.setText(Integer.toString(player_score));

        DatabaseReference DBref;
        DBref = FirebaseDatabase.getInstance().getReference();
        DBref.child("users").child(player_name).setValue(-player_score);
    }

    public void goToLeaderboard(View view) {

        Intent outgoingIntent = new Intent(this, LeaderboardActivity.class);

        outgoingIntent.putExtra(PLAYER_NAME_MESSAGE, player_name);

        startActivity(outgoingIntent);
    }
}

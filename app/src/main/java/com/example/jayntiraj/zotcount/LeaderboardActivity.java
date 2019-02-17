package com.example.jayntiraj.zotcount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.asList;

public class LeaderboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        final ListView friendsListView = findViewById(R.id.friendListView);

        final ArrayList<String> leaderboard_scores;
        leaderboard_scores = new ArrayList<String>();

        int numberOfPlayers = 0;
        int currentNum = 0;

        DatabaseReference DBref;
        DBref = FirebaseDatabase.getInstance().getReference();



        DBref.child("users").orderByValue()
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String person = snapshot.getKey();


                            leaderboard_scores.add(person+" ..... "+snapshot.getValue().toString());

                        }

                        Collections.reverse(leaderboard_scores);
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(LeaderboardActivity.this, android.R.layout.simple_list_item_1, leaderboard_scores);

                        friendsListView.setAdapter(arrayAdapter);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });







    }
}

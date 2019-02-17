package com.example.jayntiraj.zotcount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.zotaccount.MESSAGE";

    public void addScore(String name, int score) {
        DatabaseReference DBref;
        DBref = FirebaseDatabase.getInstance().getReference();
        DBref.child("users").child(name).setValue(score);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startGame(View view) {
        TextView nameText = (TextView) findViewById(R.id.editText);
        String name = nameText.getText().toString();
        if (!name.equals("")) {
            addScore(name, 0);

            Intent intent = new Intent(this, PlayGameActivity.class);
            intent.putExtra(EXTRA_MESSAGE, name);
            startActivity(intent);
        }


    }
}

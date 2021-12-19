package com.example.basketball;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class ResultsActivity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;

    Games game;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        listView = (ListView) findViewById(R.id.listView);
        game = new Games();

        ArrayList<Games> gamesList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Games");

        GamesAdapter gamesAdapter = new GamesAdapter(this, R.layout.list_row, gamesList);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    game = ds.getValue(Games.class);
                    gamesList.add(new Games(game.getDate().toString(), game.getCity().toString(), game.getTeamA().toString(), game.getTeamB().toString(), game.getScoreTeamA(), game.getScoreTeamB()));

                }
                listView.setAdapter(gamesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

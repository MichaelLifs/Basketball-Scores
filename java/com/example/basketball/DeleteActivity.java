package com.example.basketball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {


    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;

    Games game;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        listView = (ListView) findViewById(R.id.listViewUpdate);
        game = new Games();

        ArrayList<Games> gamesList = new ArrayList<>();
        gamesList.clear();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Games");

        GamesAdapter gamesAdapter = new GamesAdapter(this, R.layout.list_row, gamesList);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    game = ds.getValue(Games.class);
                    gamesList.add(new Games(game.getId(), game.getDate().toString(), game.getCity().toString(), game.getTeamA().toString(), game.getTeamB().toString(), game.getScoreTeamA(), game.getScoreTeamB()));

                }
                listView.setAdapter(gamesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener((AdapterView.OnItemClickListener) (parent, view, position, id) -> {

            AlertDialog.Builder AlertBuilder = new AlertDialog.Builder(DeleteActivity.this);
            AlertBuilder.setTitle("Warning");
            AlertBuilder.setMessage("Are you sure you want to delete this game?\n");
            AlertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Games games = gamesList.get(position);

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Query gamesQuery = ref.child("Games").orderByChild("id").equalTo(games.getId());

                    gamesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();
                            }
                            Intent intent = new Intent(DeleteActivity.this, DeleteActivity.class);
                            startActivity(intent);
                            // finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e("onCancelled", String.valueOf(databaseError.toException()));
                        }


                    });
                }

            });


            AlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertBuilder.show();


        });
    }
}
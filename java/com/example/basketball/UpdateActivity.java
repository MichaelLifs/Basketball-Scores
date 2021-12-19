package com.example.basketball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    public static final String GAME_ID = "GAME_ID";
    public static final String GAME_DATE = "GAME_DATE";
    public static final String GAME_CITY = "GAME_CITY";
    public static final String GAME_TEAM_A = "GAME_TEAM_A";
    public static final String GAME_SCORE_A = "GAME_SCORE_A";
    public static final String GAME_TEAM_B = "GAME_TEAM_B";
    public static final String GAME_SCORE_B = "GAME_SCORE_B";


    Games game;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


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


            Games games = gamesList.get(position);

            Intent intent = new Intent(this, UpdateGameAct.class);
            intent.putExtra("GAME_ID", games.getId());
            intent.putExtra("GAME_DATE", games.getDate());
            intent.putExtra("GAME_CITY", games.getCity());
            intent.putExtra("GAME_TEAM_A", games.getTeamA());
            intent.putExtra("GAME_SCORE_A", games.getScoreTeamA());
            intent.putExtra("GAME_TEAM_B", games.getTeamB());
            intent.putExtra("GAME_SCORE_B", games.getScoreTeamB());
            startActivity(intent);


        });

    }

}
package com.example.basketball;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    SearchView sv;
    RadioGroup rg;
    RadioButton rb;
    ListView listView;
    Games game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        sv = (SearchView) findViewById(R.id.sv);
        rg = (RadioGroup) findViewById(R.id.radio_group);
        rg.check(R.id.radio_city);
        listView = (ListView) findViewById(R.id.listView);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                int SelectedFilter = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(SelectedFilter);


                if (rb.getText().toString().equals("Team A")) {
                    foo("teamA", query);
                }

                if (rb.getText().toString().equals("Team B")) {
                    foo("teamB", query);
                }

                if (rb.getText().toString().equals("City")) {
                    foo("city", query);
                }

                if (rb.getText().toString().equals("Date")) {
                    foo("date", query);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //This is your adapter that will be filtered
                return false;
            }
        });


    }

    private void foo(String filter, String query) {
        ArrayList<Games> gamesList = new ArrayList<>();
        GamesAdapter gamesAdapter = new GamesAdapter(this, R.layout.list_row, gamesList);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Games");
        ref.orderByChild(filter).equalTo(query).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                    String city = userSnapshot.child("city").getValue(String.class);
                    String date = userSnapshot.child("date").getValue(String.class);
                    String teamA = userSnapshot.child("teamA").getValue(String.class);
                    String teamB = userSnapshot.child("teamB").getValue(String.class);
                    int scoreTeamA = userSnapshot.child("scoreTeamA").getValue(Integer.class);
                    int scoreTeamB = userSnapshot.child("scoreTeamB").getValue(Integer.class);
                    gamesList.add(new Games(date, city, teamA, teamB, scoreTeamA, scoreTeamB));
                }

                listView.setAdapter(gamesAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }
}
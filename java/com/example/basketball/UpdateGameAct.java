package com.example.basketball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateGameAct extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_game);

        EditText etDate = (EditText) findViewById(R.id.etDate);
        Spinner spCity = (Spinner) findViewById(R.id.spCity);
        Spinner spTeamA = (Spinner) findViewById(R.id.spTeamA);
        EditText etScoreTeamA = (EditText) findViewById(R.id.etScoreTeamA);
        Spinner spTeamB = (Spinner) findViewById(R.id.spTeamB);
        EditText etScoreTeamB = (EditText) findViewById(R.id.etScoreTeamB);
        Button updateButton = (Button) findViewById(R.id.updateButton);

        Intent intent = getIntent();

        int id = intent.getIntExtra(UpdateActivity.GAME_ID, 0);
        etDate.setText(intent.getStringExtra(UpdateActivity.GAME_DATE));
        spCity.setPrompt(intent.getStringExtra(UpdateActivity.GAME_CITY));
        spTeamA.setPrompt(intent.getStringExtra(UpdateActivity.GAME_TEAM_A));
        etScoreTeamA.setText(String.valueOf(intent.getIntExtra(UpdateActivity.GAME_SCORE_A, 0)));
        spTeamB.setPrompt(intent.getStringExtra(UpdateActivity.GAME_TEAM_B));
        etScoreTeamB.setText(String.valueOf(intent.getIntExtra(UpdateActivity.GAME_SCORE_B, 0)));


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Games");

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                String key = datas.getKey();

                                ref.child(key).child("city").setValue(spCity.getSelectedItem().toString());
                                ref.child(key).child("date").setValue(etDate.getText().toString());
                                ref.child(key).child("scoreTeamA").setValue(Integer.valueOf(etScoreTeamA.getText().toString()));
                                ref.child(key).child("scoreTeamB").setValue(Integer.valueOf(etScoreTeamB.getText().toString()));
                                ref.child(key).child("teamA").setValue(spTeamA.getSelectedItem().toString());
                                ref.child(key).child("teamB").setValue(spTeamB.getSelectedItem().toString());

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }
}
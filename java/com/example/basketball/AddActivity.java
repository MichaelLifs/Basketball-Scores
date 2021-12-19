package com.example.basketball;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class AddActivity extends AppCompatActivity {
    Spinner spinnerTeamA;
    Spinner spinnerTeamB;
    Spinner spinnerCity;
    TextView tv_msg;
    EditText scoreA;
    EditText scoreB;
    EditText date_et;
    Button AddGame;
    DatabaseReference gamesDbRef;
    Games game;

    Boolean scoreAflag, scoreBflag, dateFlag, cityFlag, teamAflag, teamBflag;
    String errorMsg = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        spinnerTeamA = findViewById(R.id.sp_teamA);
        spinnerTeamB = findViewById(R.id.sp_teamB);
        spinnerCity = findViewById(R.id.sp_city);

        spinnerCity.setSelection(0);
        scoreA = findViewById(R.id.et_teamAScore);
        scoreB = findViewById(R.id.et_teamBScore);
        date_et = findViewById(R.id.et_date);
        AddGame = findViewById(R.id.add_btn);

        tv_msg = findViewById(R.id.tv_msg);

        gamesDbRef = FirebaseDatabase.getInstance().getReference().child("Games");

        AddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorMsg = "";

                if (scoreA.getText().toString() == "")
                    scoreAflag = false;
                else
                    scoreAflag = isNumeric(scoreA.getText().toString());

                if (scoreB.getText().toString() == "")
                    scoreBflag = false;
                else
                    scoreBflag = isNumeric(scoreB.getText().toString());

                dateFlag = checkDate(date_et.getText().toString());
                if (date_et.getText().toString() == "")
                    dateFlag = false;
                cityFlag = spinnerCity.getSelectedItem().toString().equals("Select City");
                teamAflag = spinnerTeamA.getSelectedItem().toString().equals("Select Team");
                teamBflag = spinnerTeamB.getSelectedItem().toString().equals("Select Team");

                if (dateFlag == false)
                    errorMsg += "Invalid date\nFormat: dd/mm/y.\n";
                if (scoreAflag == false || scoreBflag == false)
                    errorMsg += "Score must be a numeric value.\n";
                if (teamAflag == true || teamBflag == true)
                    errorMsg += "Please select a team from the list.\n";
                if (cityFlag == true)
                    errorMsg += "Please select a city from the list.\n";

                if (errorMsg != "") {
                    tv_msg.setText(errorMsg);
                    tv_msg.setTextColor(Color.RED);
                }
                if (errorMsg == "") {
                    tv_msg.setText("Please fill in all fields correctly");
                    tv_msg.setTextColor(Color.BLACK);
                    insertGameData();
                    date_et.setText("");
                    date_et.setHint("Enter Date");
                    spinnerCity.setSelection(0);
                    spinnerTeamA.setSelection(0);
                    scoreA.setText("");
                    scoreA.setHint("Team A Score");
                    spinnerTeamB.setSelection(0);
                    scoreB.setText("");
                    scoreB.setHint("Team B Score");
                }
            }
        });
    }

    private void insertGameData() {
        String date = date_et.getText().toString();
        String city = spinnerCity.getSelectedItem().toString();
        String teamA = spinnerTeamA.getSelectedItem().toString();
        String teamB = spinnerTeamB.getSelectedItem().toString();
        int scoreTeamA = Integer.valueOf(scoreA.getText().toString());
        int scoreTeamB = Integer.valueOf(scoreB.getText().toString());
        game = new Games();

        Games g = new Games(HashId(city, teamA, teamB, scoreTeamA, scoreTeamB), date, city, teamA, teamB, scoreTeamA, scoreTeamB);
        gamesDbRef.push().setValue(g);

        Toast.makeText(AddActivity.this, "Data Inserted!", Toast.LENGTH_SHORT).show();
    }

    private int HashId(String city, String teamA, String teamB, int scoreA, int scoreB) {
        int retval = 0;
        for (int i = 0; i < city.length(); i++) {
            retval += city.charAt(i);
        }
        for (int i = 0; i < teamA.length(); i++) {
            retval += teamA.charAt(i);
        }
        for (int i = 0; i < teamB.length(); i++) {
            retval += teamB.charAt(i);
        }
        retval += scoreA + scoreB;
        return retval;
    }

    private static boolean dateValidation(String date) {
        boolean status = false;

        if (checkDate(date)) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                status = true;
            } catch (Exception e) {
                status = false;
            }
        }
        return status;
    }

    static boolean checkDate(String date) {
        String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean flag = false;
        if (date.matches(pattern)) {
            flag = true;
        }
        return flag;
    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


}
package com.example.basketball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private CardView Results;
    private CardView Add;
    private CardView Update;
    private CardView Search;
    private CardView Delete;
    private CardView About;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Results = (CardView) findViewById(R.id.res_cv);
        Add = (CardView) findViewById(R.id.add_cv);
        Update = (CardView) findViewById(R.id.update_cv);
        Search = (CardView) findViewById(R.id.search_cv);
        Delete = (CardView) findViewById(R.id.delete_cv);
        About = (CardView) findViewById(R.id.about_cv);

        Results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsActivity();
            }
        });
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateActivity();
            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteActivity();
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutActivity();
            }
        });
    }


    public void openResultsActivity() {
        Intent intent = new Intent(this, ResultsActivity.class);
        /*
        intent.putExtra("multiplier", mplier.getText().toString());
        intent.putExtra("multiplicand", mplicand.getText().toString());
         */
        startActivity(intent);
    }

    public void openAddActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void openUpdateActivity() {
        Intent intent = new Intent(this, UpdateActivity.class);
        startActivity(intent);
    }

    public void openSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void openDeleteActivity() {
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }

    public void openAboutActivity() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
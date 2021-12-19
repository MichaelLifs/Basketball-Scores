package com.example.basketball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    ImageView michaelPic;
    ImageView zoharPic;
    TextView tv_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        michaelPic = findViewById(R.id.michaelPic);
        zoharPic = findViewById(R.id.zoharPic);
        tv_about = findViewById(R.id.about_tv);

        String s = "We have built an app for the results of basketball games in the Israeli league.\n" +
                "In the application can add results, update results, delete results and search.\n" +
                "Of course it will be possible to see all the results entered.\n" +
                "The project is based on a firebase database.\n";
        tv_about.setText(s);
        michaelPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, MichaelActivity.class);
                startActivity(intent);
            }
        });

        zoharPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, ZoharActivity.class);
                startActivity(intent);
            }
        });

    }
}
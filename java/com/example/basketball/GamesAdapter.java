package com.example.basketball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import static androidx.core.graphics.drawable.IconCompat.getResources;

public class GamesAdapter extends ArrayAdapter<Games> {
    private Context mContext;
    private int mResource;


    public GamesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Games> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);
        TextView Date = convertView.findViewById(R.id.date);
        TextView City = convertView.findViewById(R.id.city);
        TextView TeamA = convertView.findViewById(R.id.txtSub);
        TextView ScoreTeamA = convertView.findViewById(R.id.txtNameA);
        TextView TeamB = convertView.findViewById(R.id.txtSubB);
        TextView ScoreTeamB = convertView.findViewById(R.id.txtNameB);
        ImageView TeamAImg = convertView.findViewById(R.id.imageA);
        ImageView TeamBImg = convertView.findViewById(R.id.imageB);

        Date.setText("Date: " + getItem(position).getDate());
        City.setText("City: " + getItem(position).getCity());
        TeamA.setText(getItem(position).getTeamA());
        ScoreTeamA.setText(String.valueOf(getItem(position).getScoreTeamA()));
        TeamB.setText(getItem(position).getTeamB());
        ScoreTeamB.setText(String.valueOf(getItem(position).getScoreTeamB()));
        TeamAImg.setImageResource(getImageId(mContext, HashTeamImg(TeamA.getText().toString())));
        TeamBImg.setImageResource(getImageId(mContext, HashTeamImg(TeamB.getText().toString())));
        return convertView;
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    public static String HashTeamImg(String name) {
        String retVal;
        int ascii = 0;
        for (int i = 0; i < name.length(); i++) {
            ascii += Integer.valueOf(name.charAt(i));
        }
        retVal = "t" + ascii;
        return retVal;
    }

}




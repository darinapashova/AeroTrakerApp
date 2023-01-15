package com.example.aerotrackerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AthleteActivity extends AppCompatActivity {
    String id, athName, athSurname, athAge, ws, we, ms, me, athEfficiency, athIntensity, athCategory, athPeriod, physicalPrep1, physicalPrep2, physicalPrep3, physicalPrep4, preparation;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete);
        id = getIntent().getStringExtra("id");
        athName = getIntent().getStringExtra("name");
        athSurname = getIntent().getStringExtra("surname");
        athAge = getIntent().getStringExtra("age");
        athEfficiency = getIntent().getStringExtra("efficiency");
        athIntensity = getIntent().getStringExtra("intensity");
        athCategory = getIntent().getStringExtra("category");
        athPeriod = getIntent().getStringExtra("period");
        ws = getIntent().getStringExtra("ws");
        we = getIntent().getStringExtra("we");
        ms = getIntent().getStringExtra("ms");
        me = getIntent().getStringExtra("me");
        physicalPrep1 = getIntent().getStringExtra("preparatory");
        physicalPrep2 = getIntent().getStringExtra("precompetitive");
        physicalPrep3 = getIntent().getStringExtra("competitive");
        physicalPrep4 = getIntent().getStringExtra("postcompetitive");
        preparation = getIntent().getStringExtra("physicalPrep");
        ok = findViewById(R.id.button_ok);

        TextView ath_id = findViewById(R.id.TVAthleteID);
        TextView ath_name = findViewById(R.id.TVAthleteName);
        TextView ath_surname = findViewById(R.id.TVAthleteSurname);
        TextView ath_age = findViewById(R.id.TVAthleteAge);
        TextView warm_start = findViewById(R.id.TVWarmUpStart);
        TextView warm_end = findViewById(R.id.TVWarmUpEnd);
        TextView main_start = findViewById(R.id.TVMainPartStart);
        TextView main_end = findViewById(R.id.TVMainPartEnd);
        TextView ath_category = findViewById(R.id.TVCategory);
        TextView ath_period = findViewById(R.id.TVPeriod);
        TextView intensity = findViewById(R.id.TVIntensity);
        TextView efficiency = findViewById(R.id.TVEfficiency);
        TextView physical = findViewById(R.id.TVPhysicalPreparation);


        ath_id.setText(String.valueOf(id));
        ath_name.setText(athName);
        ath_surname.setText(athSurname);
        ath_age.setText(athAge);
        warm_start.setText(ws);
        warm_end.setText(we);
        main_start.setText(ms);
        main_end.setText(me);
        ath_category.setText(athCategory);
        intensity.setText(athIntensity);
        efficiency.setText(athEfficiency);
        ath_period.setText(athPeriod);

        switch (athPeriod) {
            case "preparatory":
                physical.setText(physicalPrep1);
                break;
            case "precompetitive":
                physical.setText(physicalPrep2);
                break;
            case "competitive":
                physical.setText(physicalPrep3);
                break;
            case "postcompetitive":
                physical.setText(physicalPrep4);
                break;
        }
        if (physical.getText().toString().equals("")) {
            physical.setText(preparation);
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOk();
            }
        });

    }

    public void buttonOk() {
        Intent intent = new Intent(this, TrainingPlan.class);
        startActivity(intent);
    }


}
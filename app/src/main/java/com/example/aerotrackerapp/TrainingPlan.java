package com.example.aerotrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TrainingPlan extends AppCompatActivity implements AthleteRVInterface {

    private ArrayList<MergedTables> athleteActivityMerged;
    private DataBaseHelper dbHelper;
    private AthleteRVAdapter athleteRVAdapter;
    private RecyclerView athleteRV;
    Button addPhysicalPreparation;
    Button download, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_plan);
        addPhysicalPreparation = (Button) findViewById(R.id.btnAddPhysicalPrep);
        download = (Button) findViewById(R.id.btndownload);
        show = (Button) findViewById(R.id.btnShow);
        addPhysicalPreparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPreparation();
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundLoadTask backgroundLoadTask = new BackgroundLoadTask(TrainingPlan.this);
                backgroundLoadTask.execute();

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrainingPlan.this, ShowDataFromServer.class));

            }
        });

        athleteActivityMerged = new ArrayList<>();
        dbHelper = new DataBaseHelper(TrainingPlan.this);
        athleteActivityMerged = dbHelper.readMergedTablesAthleteActivity();
        athleteRVAdapter = new AthleteRVAdapter(athleteActivityMerged, this, TrainingPlan.this);
        athleteRV = findViewById(R.id.idRVAthletes);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TrainingPlan.this, RecyclerView.VERTICAL, false);
        athleteRV.setLayoutManager(linearLayoutManager);

        athleteRV.setAdapter(athleteRVAdapter);

    }

    private void openAddPreparation() {
        Intent intent = new Intent(this, AddPhysicalPreparation.class);
        startActivity(intent);
    }



    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(TrainingPlan.this, AthleteActivity.class);
        intent.putExtra("id", athleteActivityMerged.get(position).getIdAthlete());
        intent.putExtra("name", athleteActivityMerged.get(position).getAthleteName());
        intent.putExtra("surname", athleteActivityMerged.get(position).getAthleteSurname());
        intent.putExtra("age", athleteActivityMerged.get(position).getAthleteAge());
        intent.putExtra("efficiency", athleteActivityMerged.get(position).getTrainingEfficiency());
        intent.putExtra("intensity", athleteActivityMerged.get(position).getTrainingIntensity());
        intent.putExtra("category", athleteActivityMerged.get(position).getCategory());
        intent.putExtra("period", athleteActivityMerged.get(position).getPeriod());
        intent.putExtra("ws", athleteActivityMerged.get(position).getWarmUpstart());
        intent.putExtra("we", athleteActivityMerged.get(position).getWarmUpEnd());
        intent.putExtra("ms", athleteActivityMerged.get(position).getMainPartStart());
        intent.putExtra("me", athleteActivityMerged.get(position).getMainPartEnd());
        intent.putExtra("preparatory", athleteActivityMerged.get(position).getPreparatory());
        intent.putExtra("precompetitive", athleteActivityMerged.get(position).getPrecompetitive());
        intent.putExtra("competitive", athleteActivityMerged.get(position).getCompetitive());
        intent.putExtra("postcompetitive", athleteActivityMerged.get(position).getPostcompetitive());

        startActivity(intent);
    }


}


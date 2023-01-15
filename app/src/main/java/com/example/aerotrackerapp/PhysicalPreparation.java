package com.example.aerotrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PhysicalPreparation extends AppCompatActivity {
    TextView setPreparation;
    private String getPeriod = null;
    DataBaseHelper dbHelper;
    private ArrayList<TrainingPhysicalPreparation> trainingPhysicalPreparationArrayList;
    private ArrayList<MergedTables> mergedTablesArrayList;
    Button finishBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_preparation);
        setPreparation = findViewById(R.id.textViewPreparation);
        finishBtn = findViewById(R.id.btnFinishPhysicalPrep);
        getPeriod = getIntent().getStringExtra("idPeriod");

        String currentPreparation = "";
        trainingPhysicalPreparationArrayList = new ArrayList<>();

        dbHelper = new DataBaseHelper(PhysicalPreparation.this);

        int period = Integer.parseInt(getPeriod);
        trainingPhysicalPreparationArrayList = dbHelper.readPhysicalPreparation();

        if (trainingPhysicalPreparationArrayList.size() != 0) {
            if (period == 1) {

                TrainingPhysicalPreparation row = trainingPhysicalPreparationArrayList.get(trainingPhysicalPreparationArrayList.size() - 1);
                String preparatory = row.getPreparatory();
                currentPreparation = preparatory;
                setPreparation.setText(preparatory);

            } else if (period == 2) {

                TrainingPhysicalPreparation row = trainingPhysicalPreparationArrayList.get(trainingPhysicalPreparationArrayList.size() - 1);
                String precompetitive = row.getPrecompetitive();
                currentPreparation = precompetitive;
                setPreparation.setText(precompetitive);

            } else if (period == 3) {

                TrainingPhysicalPreparation row = trainingPhysicalPreparationArrayList.get(trainingPhysicalPreparationArrayList.size() - 1);
                String competitive = row.getCompetitive();
                currentPreparation = competitive;
                setPreparation.setText(competitive);

            } else if (period == 4) {

                TrainingPhysicalPreparation row = trainingPhysicalPreparationArrayList.get(trainingPhysicalPreparationArrayList.size() - 1);
                String postcompetitive = row.getPostcompetitive();
                currentPreparation = postcompetitive;
                setPreparation.setText(postcompetitive);

            }
        } else {
            setPreparation.setText("");
        }

        mergedTablesArrayList = new ArrayList<>();
        mergedTablesArrayList = dbHelper.readMergedTablesAthleteActivity();
        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        String id = String.valueOf(dbHelper.getAthlete(name, surname));
        String age = dbHelper.getAthleteAge(id);
        String efficiency = getIntent().getStringExtra("efficiency");
        String intensity = getIntent().getStringExtra("intensity");
        String ws = getIntent().getStringExtra("ws");
        String we = getIntent().getStringExtra("we");
        String ms = getIntent().getStringExtra("ms");
        String me = getIntent().getStringExtra("me");
        String category = "";
        String periodS = "";
        if (mergedTablesArrayList.size() != 0) {
            MergedTables row = mergedTablesArrayList.get(mergedTablesArrayList.size() - 1);
            String categoryInput = row.getCategory();
            category = categoryInput;
            String periodInput = row.getPeriod();
            periodS = periodInput;
        }


        String preparation = currentPreparation;
        String type = "add";
        Background background = new Background(this);
        background.execute(type, id, name, surname, age, efficiency, intensity, ws, we, ms, me, preparation, category, periodS);


        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhysicalPreparation.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }


}




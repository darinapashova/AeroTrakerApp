package com.example.aerotrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPhysicalPreparation extends AppCompatActivity {
    Button backBtn, addBtn,finishBtn;
    EditText preparatory, precompetitive, competitive, postcompetitive;
    DataBaseHelper dbHelper;
    long currentIdPrep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_physical_preparation);
        preparatory = (EditText) findViewById(R.id.preparatoryPreparation);
        precompetitive = (EditText) findViewById(R.id.precompetitivePreparation);
        competitive = (EditText) findViewById(R.id.compeptitivePreparation);
        postcompetitive = (EditText) findViewById(R.id.postcompetitivePreparation);
        dbHelper = new DataBaseHelper(this);
        addBtn = (Button) findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhysicalPreparation();
            }
        });
        backBtn = (Button) findViewById(R.id.button_backToTrainingPlan);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToTrainingPlanPage();

            }
        });

        finishBtn = (Button) findViewById(R.id.btnFinishTraining);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddPhysicalPreparation.this,MainActivity.class);
                intent.putExtra("idPrep",String.valueOf(currentIdPrep));
                startActivity(intent);
            }
        });
    }

    private void addPhysicalPreparation() {
        String preparatoryPrep = preparatory.getText().toString();
        String precompetitivePrep = precompetitive.getText().toString();
        String competitivePrep = competitive.getText().toString();
        String postCompetitive = postcompetitive.getText().toString();
        dbHelper.addPhysicalPreparation(preparatoryPrep, precompetitivePrep, competitivePrep, postCompetitive);
        Toast.makeText(AddPhysicalPreparation.this, "Successfully added.", Toast.LENGTH_SHORT).show();
        currentIdPrep=dbHelper.getIdPreparation(preparatoryPrep, precompetitivePrep, competitivePrep, postCompetitive);
        String type="preparation";
        Background background = new Background(this);
        background.execute(type,preparatoryPrep,precompetitivePrep,competitivePrep,postCompetitive);

    }

    private void goBackToTrainingPlanPage() {
        Intent intent = new Intent(this, TrainingPlan.class);
        intent.putExtra("idPrep",String.valueOf(currentIdPrep));
        startActivity(intent);
    }
}
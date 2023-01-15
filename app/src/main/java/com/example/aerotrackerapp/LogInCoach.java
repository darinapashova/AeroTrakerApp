package com.example.aerotrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LogInCoach extends AppCompatActivity {
    private EditText coachNameEdit, coachSurnameEdit, coachPasswordEdit;
    private Button btnLogIn, backBtn;
    DataBaseHelper dbHelper;
    String json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_coach);
        coachNameEdit = (EditText) findViewById(R.id.nameCoachLogIn);
        coachSurnameEdit = (EditText) findViewById(R.id.surnameCoachLogIn);
        coachPasswordEdit = (EditText) findViewById(R.id.passwordCoachLogIn);


        btnLogIn = (Button) findViewById(R.id.btnCoachLogIn);
        dbHelper = new DataBaseHelper(this);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userFirstName = coachNameEdit.getText().toString();
                String userLastName = coachSurnameEdit.getText().toString();
                String pass = coachPasswordEdit.getText().toString();
                if (userFirstName.equals("") || userLastName.equals("") || pass.equals("")) {
                    Toast.makeText(LogInCoach.this, "Fill all data.", Toast.LENGTH_SHORT).show();

                } else {
                    boolean result = dbHelper.checkNameAndPasswordForCoach(userFirstName, pass);

                    if (result == true) {

                        Intent i = new Intent(LogInCoach.this, TrainingPlan.class);
                        i.putExtra("name", userFirstName);
                        i.putExtra("surname", userLastName);
                        startActivity(i);

                    } else {
                        Toast.makeText(LogInCoach.this, "Invalid user", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });


        backBtn = (Button) findViewById(R.id.button_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToMainPage();
            }
        });
    }

    public void goBackToMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }



}
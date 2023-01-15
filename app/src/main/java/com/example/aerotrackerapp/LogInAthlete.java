package com.example.aerotrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInAthlete extends AppCompatActivity {
    private EditText athleteNameEdit, athleteSurnameEdit, athletePasswordEdit;
    private Button btnLogIn, backBtn;
    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_athlete);
        athleteNameEdit = (EditText) findViewById(R.id.nameAthleteLogIn);
        athleteSurnameEdit = (EditText) findViewById(R.id.surnameAthleteLogIn);
        athletePasswordEdit = (EditText) findViewById(R.id.passwordAthleteLogIn);


        btnLogIn = (Button) findViewById(R.id.btnAthleteLogIn);
        dbHelper = new DataBaseHelper(this);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userFirstName = athleteNameEdit.getText().toString();
                String userLastName = athleteSurnameEdit.getText().toString();
                String pass = athletePasswordEdit.getText().toString();
                if (userFirstName.equals("") || userLastName.equals("") || pass.equals("")) {
                    Toast.makeText(LogInAthlete.this, "Fill all data.", Toast.LENGTH_SHORT).show();

                } else {
                    boolean result = dbHelper.checkNameAndPasswordForAthlete(userFirstName, pass);
                    if (result == true) {
                        Intent intent = new Intent(getApplicationContext(), Training.class);
                        intent.putExtra("name", userFirstName);
                        intent.putExtra("surname", userLastName);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LogInAthlete.this, "Invalid user", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });


        backBtn = (Button) findViewById(R.id.button_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToPreviousPage();
            }
        });
    }

    public void goBackToPreviousPage() {
        Intent intent = new Intent(this, AthleteRegistration.class);
        startActivity(intent);

    }
}
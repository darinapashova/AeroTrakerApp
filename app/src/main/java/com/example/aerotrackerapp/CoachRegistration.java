package com.example.aerotrackerapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CoachRegistration extends AppCompatActivity {
    // creating variables for our edittext, button and dbhandler
    private EditText coachNameEdit, coachSurnameEdit, coachType, coachPasswordEdit, coachRePasswordEdit;
    private Button btnRegister, btnLogIn, backBtn;
    DataBaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_registration);
        coachNameEdit = (EditText) findViewById(R.id.nameCoachRegister);
        coachSurnameEdit = (EditText) findViewById(R.id.coachSurnameRegister);
        coachType = (EditText) findViewById(R.id.coachTypeRegister);
        coachPasswordEdit = (EditText) findViewById(R.id.coachPasswordRegister);
        coachRePasswordEdit = (EditText) findViewById(R.id.coachRePasswordRegister);
        btnRegister = (Button) findViewById(R.id.btnRegisterCoach);
        btnLogIn = (Button) findViewById(R.id.btnLogInCoach);
        dbHelper = new DataBaseHelper(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coachRegistrationMethod();
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coachOpenLogInActivity();
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

    public void coachRegistrationMethod() {
        String userFirstName = coachNameEdit.getText().toString();
        String userLastName = coachSurnameEdit.getText().toString();
        String trainingType = coachType.getText().toString();
        String pass = coachPasswordEdit.getText().toString();
        String repass = coachRePasswordEdit.getText().toString();
        if (userFirstName.equals("") || userLastName.equals("") || pass.equals("") || repass.equals("")) {
            Toast.makeText(CoachRegistration.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();

        } else {
            if (pass.equals(repass)) {
                boolean userCheck = dbHelper.checkIfCoachExistsIntoDataBase(userFirstName, userLastName);
                if (!userCheck) {
                  dbHelper.addNewCoach(userFirstName, userLastName, trainingType, pass);
                    Toast.makeText(CoachRegistration.this, "Successful registration.", Toast.LENGTH_SHORT).show();
                    String type = "coach";
                    Background background = new Background(this);
                    background.execute(type, userFirstName, userLastName, trainingType, pass);
                    Intent intent = new Intent(getApplicationContext(), TrainingPlan.class);

                    startActivity(intent);
                } else if (userCheck) {
                    Toast.makeText(CoachRegistration.this, "Coach already exists.", Toast.LENGTH_SHORT).show();
                    coachNameEdit.setText("");
                    coachSurnameEdit.setText("");
                    coachType.setText("");
                    coachPasswordEdit.setText("");
                    coachRePasswordEdit.setText("");
                }
            } else {
                Toast.makeText(CoachRegistration.this, "Password not matching", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void coachOpenLogInActivity() {
        Intent intent = new Intent(this, LogInCoach.class);
        startActivity(intent);
    }


}

package com.example.aerotrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class AthleteRegistration extends AppCompatActivity {
    // creating variables for our edittext, button and dbHelper
    private EditText athleteNameEdit, athleteSurnameEdit, athletePasswordEdit, athleteRePasswordEdit;
    private Button btnRegister, btnLogIn, backBtn;
    DataBaseHelper dbHelper;
    TextView birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_registration);
        athleteNameEdit = findViewById(R.id.nameAthleteRegister);
        athleteSurnameEdit = findViewById(R.id.athleteSurnameRegister);
        birthDate = findViewById(R.id.athleteAgeRegister);
        athletePasswordEdit = findViewById(R.id.athletePasswordRegister);
        athleteRePasswordEdit = findViewById(R.id.athleteRePasswordRegister);
        btnRegister = findViewById(R.id.btnRegisterAthlete);
        btnLogIn = findViewById(R.id.btnLogInAthlete);
        dbHelper = new DataBaseHelper(this);
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AthleteRegistration.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                birthDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                athleteRegistrationMethod();
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                athleteOpenLogInActivity();
            }

        });
        backBtn = findViewById(R.id.button_back);
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

    public void athleteRegistrationMethod() {
        String userFirstName = athleteNameEdit.getText().toString();
        String userLastName = athleteSurnameEdit.getText().toString();
        String birthDateInput = birthDate.getText().toString();
        String pass = athletePasswordEdit.getText().toString();
        String repass = athleteRePasswordEdit.getText().toString();

        if (userFirstName.equals("") || userLastName.equals("") || pass.equals("") || repass.equals("")) {
            Toast.makeText(AthleteRegistration.this, "Please enter all the data...", Toast.LENGTH_SHORT).show();
        } else {
            if (pass.equals(repass)) {
                boolean userCheck = dbHelper.checkIfAthleteExistsIntoDataBase(userFirstName, userLastName);
                if (!userCheck) {
                    dbHelper.addNewAthlete(userFirstName, userLastName, birthDateInput, pass);
                    String type = "athlete";
                    Background background = new Background(this);
                    background.execute(type, userFirstName, userLastName, birthDateInput, pass);
                    Toast.makeText(AthleteRegistration.this, "Successful registration.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Training.class);
                    intent.putExtra("name", userFirstName);
                    intent.putExtra("surname", userLastName);
                    startActivity(intent);
                } else if (userCheck) {
                    Toast.makeText(AthleteRegistration.this, "Athlete already exists.", Toast.LENGTH_SHORT).show();
                    athleteNameEdit.setText("");
                    athleteSurnameEdit.setText("");
                    birthDate.setText("");
                    athletePasswordEdit.setText("");
                    athleteRePasswordEdit.setText("");
                }
            } else {
                Toast.makeText(AthleteRegistration.this, "Password not matching", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void athleteOpenLogInActivity() {
        Intent intent = new Intent(this, LogInAthlete.class);
        startActivity(intent);

    }

}
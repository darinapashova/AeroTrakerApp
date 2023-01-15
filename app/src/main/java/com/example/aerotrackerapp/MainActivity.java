package com.example.aerotrackerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button athleteBtn, coachBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        athleteBtn = findViewById(R.id.button_athlete);
        athleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAthleteRegistration();
            }
        });
        coachBtn = findViewById(R.id.button_coach);
        coachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoachRegistrationCoach();
            }
        });
    }
    public void openAthleteRegistration() {
        Intent intent = new Intent(this, AthleteRegistration.class);
        startActivity(intent);

    }

    public void openCoachRegistrationCoach() {
        Intent intent = new Intent(this, CoachRegistration.class);
        startActivity(intent);

    }

}

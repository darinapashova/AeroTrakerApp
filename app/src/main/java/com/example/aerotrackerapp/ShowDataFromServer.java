package com.example.aerotrackerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ShowDataFromServer extends AppCompatActivity implements AthleteRVInterface {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MergedTables> arrayList = new ArrayList<>();
    ArrayList<MergedTables> list = new ArrayList<>();
    Button back, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_from_server);
        recyclerView = (RecyclerView) findViewById(R.id.idRVAthletesFromServer);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = dataBaseHelper.getInfoFromServer(sqLiteDatabase);
        cursor.moveToFirst();
        do {
            MergedTables mergedTables = new MergedTables(cursor.getString(1), cursor.getString(2), cursor.getString(8), cursor.getString(9), cursor.getString(11));
            arrayList.add(mergedTables);
            MergedTables tables = new MergedTables(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(11), cursor.getString(12),cursor.getString(10));
            list.add(tables);
        } while (cursor.moveToNext());
        dataBaseHelper.close();
        adapter = new AdapterForServerInfo(arrayList, this);
        recyclerView.setAdapter(adapter);
        back = (Button) findViewById(R.id.back);
        logout = (Button) findViewById(R.id.btnFinishLogOut);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDataFromServer.this, TrainingPlan.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDataFromServer.this, MainActivity.class));
            }
        });

    }


    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(ShowDataFromServer.this, AthleteActivity.class);
        intent.putExtra("id", list.get(position).getIdAthlete());
        intent.putExtra("name", list.get(position).getAthleteName());
        intent.putExtra("surname", list.get(position).getAthleteSurname());
        intent.putExtra("age", list.get(position).getAthleteAge());
        intent.putExtra("efficiency", list.get(position).getTrainingEfficiency());
        intent.putExtra("intensity", list.get(position).getTrainingIntensity());
        intent.putExtra("category", list.get(position).getCategory());
        intent.putExtra("period", list.get(position).getPeriod());
        intent.putExtra("ws", list.get(position).getWarmUpstart());
        intent.putExtra("we", list.get(position).getWarmUpEnd());
        intent.putExtra("ms", list.get(position).getMainPartStart());
        intent.putExtra("me", list.get(position).getMainPartEnd());
        intent.putExtra("preparatory", list.get(position).getPreparatory());
        intent.putExtra("precompetitive", list.get(position).getPrecompetitive());
        intent.putExtra("competitive", list.get(position).getCompetitive());
        intent.putExtra("postcompetitive", list.get(position).getPostcompetitive());
        intent.putExtra("physicalPrep",list.get(position).getPreparation());

        startActivity(intent);
    }
}


package com.example.aerotrackerapp;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AthleteRVAdapter extends RecyclerView.Adapter<AthleteRVAdapter.ViewHolder> {

    private final ArrayList<MergedTables> mergedTablesArrayList;
    private final AthleteRVInterface athleteRVInterface;
    private final Context context;

    public AthleteRVAdapter(ArrayList<MergedTables> mergedTablesArrayList, AthleteRVInterface athleteRVInterface, Context context) {

        this.mergedTablesArrayList = mergedTablesArrayList;
        this.context = context;
        this.athleteRVInterface = athleteRVInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_athlete_rv_item, parent, false);
        return new ViewHolder(view, athleteRVInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MergedTables mergedTables = mergedTablesArrayList.get(position);
        holder.nameTV.setText(mergedTables.getAthleteName());
        holder.surnameTV.setText(mergedTables.getAthleteSurname());
        holder.efficiencyTV.setText(mergedTables.getTrainingEfficiency());
        holder.intensityTV.setText(mergedTables.getTrainingIntensity());
        holder.categoryTV.setText(mergedTables.getCategory());

    }

    @Override
    public int getItemCount() {
        return mergedTablesArrayList.size();

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView efficiencyTV;
        private final TextView intensityTV;
        private final TextView categoryTV;
        private final TextView nameTV;
        private final TextView surnameTV;

        public ViewHolder(@NonNull View itemView, AthleteRVInterface athleteRVInterface) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.idTVAthleteName);
            surnameTV = itemView.findViewById(R.id.idTVAthleteSurname);
            efficiencyTV = itemView.findViewById(R.id.idTVEfficiency);
            intensityTV = itemView.findViewById(R.id.idTVIntensity);
            categoryTV = itemView.findViewById(R.id.idTVCategory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (athleteRVInterface != null) {
                        int pos = getAbsoluteAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            athleteRVInterface.onItemClicked(pos);
                        }
                    }
                }
            });

        }
    }

}

package com.example.aerotrackerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterForServerInfo extends RecyclerView.Adapter<AdapterForServerInfo.RecyclerViewHolder> {
    ArrayList<MergedTables> arrayList = new ArrayList<>();
    private final AthleteRVInterface athleteRVInterface;

    public AdapterForServerInfo(ArrayList<MergedTables> arrayList,AthleteRVInterface athleteRVInterface) {
        this.arrayList = arrayList;
        this.athleteRVInterface = athleteRVInterface;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.athlete_rv_server, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,athleteRVInterface);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        MergedTables mergedTables = arrayList.get(position);
        holder.nameTV.setText(mergedTables.getAthleteName());
        holder.surnameTV.setText(mergedTables.getAthleteSurname());
        holder.efficiencyTV.setText(mergedTables.getTrainingEfficiency());
        holder.intensityTV.setText(mergedTables.getTrainingIntensity());
        holder.categoryTV.setText(mergedTables.getCategory());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private final TextView efficiencyTV;
        private final TextView intensityTV;
        private final TextView categoryTV;
        private final TextView nameTV;
        private final TextView surnameTV;

        public RecyclerViewHolder(@NonNull View itemView, AthleteRVInterface athleteRVInterface) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.idTVAthleteNameSrver);
            surnameTV = itemView.findViewById(R.id.idTVAthleteSurnameServer);
            efficiencyTV = itemView.findViewById(R.id.idTVEfficiencyServer);
            intensityTV = itemView.findViewById(R.id.idTVIntensityServer);
            categoryTV = itemView.findViewById(R.id.idTVCategoryServer);
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

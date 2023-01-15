package com.example.aerotrackerapp;

import java.util.ArrayList;

public class AthleteResults {
    private String athleteID;
    private String trainingEfficiency;
    private String trainingIntensity;
    private String categoryID;
    private String periodID;
    private int idResults;


    /*public AthleteResults(String athleteID, String trainingEfficiency, String trainingIntensity, String categoryID, String periodID) {
        this.athleteID = athleteID;
        this.trainingEfficiency = trainingEfficiency;
        this.trainingIntensity = trainingIntensity;
        this.categoryID = categoryID;
        this.periodID = periodID;

    }
*/
    public AthleteResults( String trainingEfficiency, String trainingIntensity) {

        this.trainingEfficiency = trainingEfficiency;
        this.trainingIntensity = trainingIntensity;
    }

    public String getAthleteID() {
        return athleteID;
    }

    public void setAthleteID(String athleteID) {
        this.athleteID = athleteID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getPeriodID() {
        return periodID;
    }

    public void setPeriodID(String periodID) {
        this.periodID = periodID;
    }

    public String getTrainingEfficiency() {
        return trainingEfficiency;
    }

    public void setTrainingEfficiency(String trainingEfficiency) {
        this.trainingEfficiency = trainingEfficiency;
    }

    public String getTrainingIntensity() {
        return trainingIntensity;
    }

    public void setTrainingIntensity(String trainingIntensity) {
        this.trainingIntensity = trainingIntensity;
    }

    public int getIdResults() {
        return idResults;
    }

    public void setIdResults(int idResults) {
        this.idResults = idResults;
    }


}

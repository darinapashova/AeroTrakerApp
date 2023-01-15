package com.example.aerotrackerapp;

public class TrainingPeriod {
    private String trainingPeriod;
    private int id;

    public String getTrainingPeriod() {
        return trainingPeriod;
    }

    public void setTrainingPeriod(String trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrainingPeriod(String trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }
}

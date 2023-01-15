package com.example.aerotrackerapp;

public class TrainingHours {
    private String warmUpstart;
    private String warmUpEnd;
    private String mainPartStart;
    private String mainPartEnd;
    private int id;

    public TrainingHours(String warmUpstart, String warmUpEnd, String mainPartStart, String mainPartEnd) {
        this.warmUpstart = warmUpstart;
        this.warmUpEnd = warmUpEnd;
        this.mainPartStart = mainPartStart;
        this.mainPartEnd = mainPartEnd;
    }

    public String getWarmUpstart() {
        return warmUpstart;
    }

    public void setWarmUpstart(String warmUpstart) {
        this.warmUpstart = warmUpstart;
    }

    public String getWarmUpEnd() {
        return warmUpEnd;
    }

    public void setWarmUpEnd(String warmUpEnd) {
        this.warmUpEnd = warmUpEnd;
    }

    public String getMainPartStart() {
        return mainPartStart;
    }

    public void setMainPartStart(String mainPartStart) {
        this.mainPartStart = mainPartStart;
    }

    public String getMainPartEnd() {
        return mainPartEnd;
    }

    public void setMainPartEnd(String mainPartEnd) {
        this.mainPartEnd = mainPartEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

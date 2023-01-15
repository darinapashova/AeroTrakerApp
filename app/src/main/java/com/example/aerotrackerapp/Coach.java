package com.example.aerotrackerapp;

public class Coach {
    // variables for our name,surname,age, type.
    private String coachName;
    private String coachSurname;
    private String coachType;
    private int id;

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachSurname() {
        return coachSurname;
    }

    public void setCoachSurname(String coachSurname) {
        this.coachSurname = coachSurname;
    }

    public String getCoachType() {
        return coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coach(int coachId, String coachName, String coachSurname, String coachType) {
        this.id = coachId;
        this.coachName = coachName;
        this.coachSurname = coachSurname;
        this.coachType = coachType;
    }
}

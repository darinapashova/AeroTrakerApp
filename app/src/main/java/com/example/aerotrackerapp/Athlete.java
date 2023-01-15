package com.example.aerotrackerapp;

public class Athlete {

    // variables for our name,surname,age, id.
    private int id;
    private String athleteName;
    private String athleteSurname;
    private String athleteAge;
    private String athletePassword;

    public Athlete(int id, String athleteName, String athleteSurname, String athleteAge, String athletePassword) {
        this.id = id;
        this.athleteName = athleteName;
        this.athleteSurname = athleteSurname;
        this.athleteAge = athleteAge;
        this.athletePassword = athletePassword;
    }

// creating getter and setter methods

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getAthleteSurname() {
        return athleteSurname;
    }

    public void setAthleteSurname(String athleteSurname) {
        this.athleteSurname = athleteSurname;
    }

    public String getAthleteAge() {
        return athleteAge;
    }

    public void setAthleteAge(String athleteAge) {
        this.athleteAge = athleteAge;
    }

    public String getAthletePassword() {
        return athletePassword;
    }

    public void setAthletePassword(String athletePassword) {
        this.athletePassword = athletePassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}

package com.example.aerotrackerapp;

public class MergedTables extends AthleteResults {
    private String idAthlete;
    private String athleteName;
    private String athleteSurname;
    private String athleteAge;
    private String category;
    private String period;
    private String warmUpstart;
    private String warmUpEnd;
    private String mainPartStart;
    private String mainPartEnd;
    private String preparatory;
    private String precompetitive;
    private String competitive;
    private String postcompetitive;
    private String preparation;

    public MergedTables(String athleteName, String athleteSurname, String trainingEfficiency, String trainingIntensity, String category) {
        super(trainingEfficiency, trainingIntensity);
        this.athleteName = athleteName;
        this.athleteSurname = athleteSurname;
        this.category = category;
    }

    public MergedTables(String idAthlete, String athleteName, String athleteSurname, String athleteAge, String trainingEfficiency, String trainingIntensity, String category, String period, String ws, String we, String ms, String me, String preparatory, String precompetitive, String competitive, String postcompetitive) {

        super(trainingEfficiency, trainingIntensity);
        this.idAthlete = idAthlete;
        this.athleteName = athleteName;
        this.athleteSurname = athleteSurname;
        this.athleteAge = athleteAge;
        this.category = category;
        this.period = period;
        this.warmUpstart = ws;
        this.warmUpEnd = we;
        this.mainPartStart = ms;
        this.mainPartEnd = me;
        this.preparatory = preparatory;
        this.precompetitive = precompetitive;
        this.competitive = competitive;
        this.postcompetitive = postcompetitive;
    }

    public MergedTables(String string, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String trainingEfficiency, String trainingIntensity, String string10, String string11, String string12) {
        super(trainingEfficiency, trainingIntensity);
        this.idAthlete = string;
        this.athleteName = string1;
        this.athleteSurname = string2;
        this.athleteAge = string3;
        this.warmUpstart = string4;
        this.warmUpEnd = string5;
        this.mainPartStart = string6;
        this.mainPartEnd = string7;
        this.category = string10;
        this.period = string11;
        this.preparation = string12;

    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getPreparatory() {
        return preparatory;
    }

    public void setPreparatory(String preparatory) {
        this.preparatory = preparatory;
    }

    public String getPrecompetitive() {
        return precompetitive;
    }

    public void setPrecompetitive(String precompetitive) {
        this.precompetitive = precompetitive;
    }

    public String getCompetitive() {
        return competitive;
    }

    public void setCompetitive(String competitive) {
        this.competitive = competitive;
    }

    public String getPostcompetitive() {
        return postcompetitive;
    }

    public void setPostcompetitive(String postcompetitive) {
        this.postcompetitive = postcompetitive;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdAthlete() {
        return idAthlete;
    }

    public void setIdAthlete(String idAthlete) {
        this.idAthlete = idAthlete;
    }

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
}

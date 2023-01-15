package com.example.aerotrackerapp;

public class TrainingPhysicalPreparation {
    private String preparatory;
    private String precompetitive;
    private String competitive;
    private String postcompetitive;
    private String id;


    public TrainingPhysicalPreparation(String preparatory, String precompetitive, String competitive, String postcompetitive, String id) {
        this.preparatory = preparatory;
        this.precompetitive = precompetitive;
        this.competitive = competitive;
        this.postcompetitive = postcompetitive;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

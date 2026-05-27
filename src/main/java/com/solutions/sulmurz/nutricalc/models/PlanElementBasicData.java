package com.solutions.sulmurz.nutricalc.models;

public class PlanElementBasicData {
    private String name;
    private String description;

    public PlanElementBasicData(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

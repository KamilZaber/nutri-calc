package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

import java.util.LinkedList;
import java.util.List;

public class PlanModel extends PlanElementModel {
    @Expose
    private int planID;
    @Expose
    private int[][] elementsList;

    public PlanModel() {
        super();
        this.planID = NutriCalcModel.giveplanID();
        this.elementsList = null;
    }

    public PlanModel(int planID, int[][] elementsList) {
        super();
        this.planID = planID;
        this.elementsList = elementsList;
    }

    public int getPlanID() {
        return planID;
    }

    public int[][] getElementsList() {
        return elementsList;
    }
}

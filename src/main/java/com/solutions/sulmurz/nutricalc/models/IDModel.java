package com.solutions.sulmurz.nutricalc.models;

import java.util.ArrayList;
import java.util.List;

public class IDModel {
    private final List<Integer> IDsList = new ArrayList<>();

    public IDModel(List<? extends PlanElementModel> elementsList) {
        for(PlanElementModel element: elementsList) {
            IDsList.add(element.getID());
        }
    }

    public int giveID() {
        int freeID = 0;
        while(IDsList.contains(freeID)) {
            freeID++;
        }
        IDsList.add(freeID);
        return freeID;
    }

    public void freeID(int ID) {
        IDsList.remove((Integer) ID);
    }
}

package com.engine.realestatesearchapp.repositiories.enums;

public enum PlotType {
    RECREATIONAL("Rekreacyjne"),
    BUILDING("Budowlane"),
    AGRICULTURAL("Rolne"),
    FOREST("Leśne"),
    INVESTMENT("Inwestycyjne"),
    AGRI_CONTRUCTION("Rolno-budowlane"),
    HABITAT("Siedliskowe"),
    ALLOTMENT_GARDEN("Ogródek działkowy");

    public final String label;

    PlotType(String label) {
        this.label = label;
    }

}

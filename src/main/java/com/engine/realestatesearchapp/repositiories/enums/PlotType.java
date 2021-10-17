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

    public String getLabel() {
        return label;
    }

    public static PlotType valueOfLabel(String label) {
        for (PlotType e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }

}

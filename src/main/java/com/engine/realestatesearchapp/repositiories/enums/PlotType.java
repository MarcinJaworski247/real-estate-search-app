package com.engine.realestatesearchapp.repositiories.enums;

import com.engine.realestatesearchapp.utilities.exceptions.InvalidRequestException;

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
        throw new InvalidRequestException(String.format("Invalid label %s - doesn't exist in enum PlotType", label));
    }

}

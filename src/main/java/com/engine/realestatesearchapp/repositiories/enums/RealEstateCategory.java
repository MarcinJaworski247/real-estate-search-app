package com.engine.realestatesearchapp.repositiories.enums;

public enum RealEstateCategory {
    FLATS("Mieszkania"),
    HOUSES("Domy"),
    PLOTS("Działki"),
    OFFICES_AND_PREMISES("Biura i lokale"),
    GARAGES_AND_PARKING_LOTS("Garaże i parkingi"),
    EMPLOYEE_QUARTERS("Kwatery pracownicze"),
    ROOMS("Stancje i pokoje"),
    WAREHOUSES("Magazyny"),
    OTHER("Pozostałe");

    public final String label;

    RealEstateCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static RealEstateCategory valueOfLabel(String label) {
        for (RealEstateCategory e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}

package com.engine.realestatesearchapp.repositiories.enums;

public enum RealEstateCategory {
    FLATS("Miaszkania"),
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
}

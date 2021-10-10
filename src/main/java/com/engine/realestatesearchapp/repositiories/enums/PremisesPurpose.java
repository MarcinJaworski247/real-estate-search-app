package com.engine.realestatesearchapp.repositiories.enums;

public enum PremisesPurpose {

    SERVICE("Usługowe"),
    OFFICE("Biurowe"),
    COMMERCIAL("Handlowe"),
    GASTRONOMIC("Gastronomiczne"),
    INDUSTRIAL("Przemysłowe"),
    HOTEL("Hotelowe");

    public final String label;

    PremisesPurpose(String label) {
        this.label = label;
    }
}

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

    public String getLabel() {
        return label;
    }

    public static PremisesPurpose valueOfLabel(String label) {
        for (PremisesPurpose e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}

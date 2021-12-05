package com.engine.realestatesearchapp.repositiories.enums;

import com.engine.realestatesearchapp.utilities.exceptions.InvalidRequestException;

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
        throw new InvalidRequestException(String.format("Invalid label %s - doesn't exist in enum PremisesPurpose",
                label));
    }
}

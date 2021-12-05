package com.engine.realestatesearchapp.repositiories.enums;

import com.engine.realestatesearchapp.utilities.exceptions.InvalidRequestException;

public enum OfferType {
    RENT("Wynajem"),
    SALE("Sprzedaż");

    public final String label;

    OfferType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static OfferType valueOfLabel(String label) {
        for (OfferType e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        throw new InvalidRequestException(String.format("Invalid label %s - doesn't exist in enum OfferType", label));
    }

}

package com.engine.realestatesearchapp.repositiories.enums;

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
}

package com.engine.realestatesearchapp.repositiories.enums;

public enum FlatType {

    BLOCK_OF_FLATS("Blok"),
    TENEMENT("Kamienica"),
    APARTMENT_BUILDING("Apartamentowiec"),
    LOFT("Loft"),
    OTHER("Pozostałe");

    public final String label;

    FlatType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

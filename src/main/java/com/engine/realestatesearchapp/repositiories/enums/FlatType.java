package com.engine.realestatesearchapp.repositiories.enums;

public enum FlatType {

    BLOCK_OF_FLATS("Bloki"),
    TENEMENT("Kamienice"),
    APARTMENT_BUILDING("Apartamentowce"),
    LOFT("Lofty"),
    OTHER("Pozostałe");

    public final String label;

    FlatType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static FlatType valueOfLabel(String label) {
        for (FlatType e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}

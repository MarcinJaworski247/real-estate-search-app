package com.engine.realestatesearchapp.repositiories.enums;

public enum HouseType {

    DETACHED_HOUSE("Wolnostojące"),
    TWIN("Bliźniaki"),
    TERRACED("Szeregowce"),
    FARM("Gospodarstwa"),
    SUMMER("Letniskowe"),
    OTHER("Pozostałe");

    public final String label;

    HouseType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static HouseType valueOfLabel(String label) {
        for (HouseType e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}

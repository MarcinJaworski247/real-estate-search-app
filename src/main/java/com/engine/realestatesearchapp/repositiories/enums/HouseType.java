package com.engine.realestatesearchapp.repositiories.enums;

public enum HouseType {

    DETACHED_HOUSE("Dom wolnostojący"),
    TWIN("Bliźniak"),
    TERRACED("Szeregowiec"),
    FARM("Gospodarstwo"),
    SUMMER("Dom letniskowy"),
    OTHER("Pozostałe");

    public final String label;

    HouseType(String label) {
        this.label = label;
    }

}

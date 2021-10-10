package com.engine.realestatesearchapp.repositiories.enums;

public enum RoomType {
    ONE_PERSON("Jednoosobowe"),
    TWO_PEOPLE("Dwuosobowe"),
    THREE_PEOPLE_AND_MORE("Trzyosobowe i więcej");

    public final String label;

    RoomType(String label) {
        this.label = label;
    }
}

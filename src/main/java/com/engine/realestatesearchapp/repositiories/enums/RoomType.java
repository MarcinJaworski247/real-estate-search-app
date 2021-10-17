package com.engine.realestatesearchapp.repositiories.enums;

public enum RoomType {
    ONE_PERSON("Jednoosobowe"),
    TWO_PEOPLE("Dwuosobowe"),
    THREE_PEOPLE_AND_MORE("Trzyosobowe i więcej");

    public final String label;

    RoomType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static RoomType valueOfLabel(String label) {
        for (RoomType e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}

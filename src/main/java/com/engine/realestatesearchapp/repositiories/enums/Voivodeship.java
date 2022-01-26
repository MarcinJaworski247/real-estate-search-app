package com.engine.realestatesearchapp.repositiories.enums;

import com.engine.realestatesearchapp.utilities.exceptions.InvalidRequestException;

public enum Voivodeship {
    DOLNOSLASKIE("dolnośląskie"),
    KUJAWSKO_POMORSKIE("kujawsko-pomorskie"),
    LUBELSKIE("lubelskie"),
    LUBUSKIE("lubuskie"),
    LODZKIE("łódzkie"),
    MALOPOLSKIE("małopolskie"),
    MAZOWIECKIE("mazowieckie"),
    OPOLSKIE("opolskie"),
    PODKARPACKIE("podkarpackie"),
    PODLASKIE("podlaskie"),
    POMORSKIE("pomorskie"),
    SLASKIE("śląskie"),
    SWIETOKRZYSKIE("świętokrzyskie"),
    WARMINSKO_MAZURSKIE("warmińsko-mazurskie"),
    WIELKOPOLSKIE("wielkopolskie"),
    ZACHODNIO_POMORSKIE("zachodnio-pomorskie");

    public final String label;

    Voivodeship(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Voivodeship valueOfLabel(String label) {
        for (Voivodeship e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        throw new InvalidRequestException(String.format("Invalid label %s - doesn't exist in enum Voivodeship", label));
    }
}

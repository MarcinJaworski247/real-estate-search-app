package com.engine.realestatesearchapp.repositiories.enums;

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
}

package com.RaceKatteKlubben.MaineCoonClub.domain;

public enum Sex {
    FEMALE("Hunkøn"),
    MALE("Hankøn");

    private final String displayValue;

    private Sex(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }
}

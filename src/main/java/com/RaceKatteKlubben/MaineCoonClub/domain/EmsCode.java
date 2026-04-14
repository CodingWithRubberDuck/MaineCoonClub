package com.RaceKatteKlubben.MaineCoonClub.domain;

public enum EmsCode {
    MCO_GR1("MCO gr.1"),
    MCO_GR2("MCO gr.2"),
    MCO_GR3("MCO gr.3"),
    MCO_GR4("MCO gr.4"),
    MCO_GR5("MCO gr.5"),
    MCO_GR6("MCO gr.6"),
    MCO_GR7("MCO gr.7"),
    MCO_GR8("MCO gr.8"),
    MCO_GR9("MCO gr.9");

    private final String displayValue;

    private EmsCode(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }
}

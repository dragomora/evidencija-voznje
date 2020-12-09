package com.example.android.data.model;

public enum StanjeVozila {

    Oštećeno   ("Oštećeno"),
    Neoštećeno ("Neoštećeno");

    private final String stanjeVozila;

    StanjeVozila (String stanjeVozila) {
        this.stanjeVozila = stanjeVozila;
    }

    public String getStanje() {
        return stanjeVozila;
    }


    @Override
    public String toString(){
        return this.getStanje();
    }


}


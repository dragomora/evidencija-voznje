package com.example.android.data.model;

public enum SvrhaVoznje {

    Službeno ("Poslovno"),
    Privatno ("Privatno");

    private final String svrha;

    SvrhaVoznje (String svrha) {
        this.svrha = svrha;
    }

    public String getSvrha() {
        return svrha;
    }
}

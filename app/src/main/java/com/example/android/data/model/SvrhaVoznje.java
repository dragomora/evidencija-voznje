package com.example.android.data.model;

public enum SvrhaVoznje {

    Službeno ("Službeno"),
    Privatno ("Službeno");

    private final String svrha;

    SvrhaVoznje (String svrha) {
        this.svrha = svrha;
    }

    public String getSvrha() {
        return svrha;
    }

    @Override
    public String toString(){
        return this.getSvrha();
    }

}

package com.example.android.data.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vozilo {

    private String id;
    private Date datum_registracije;
    private Integer predjenoKm;
    private String registarskiBroj;
    private String naziv;
    private Boolean zauzeto;
    private Map<String,Object> additionalProperties = new HashMap<String,Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDatum_registracije() {
        return datum_registracije;
    }

    public void setDatum_registracije(Date datum_registracije) {
        this.datum_registracije = datum_registracije;
    }

    public Integer getPredjenoKm() {
        return predjenoKm;
    }

    public void setPredjenoKm(Integer predjenoKm) {
        this.predjenoKm = predjenoKm;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Boolean getZauzeto() {
        return zauzeto;
    }

    public void setZauzeto(Boolean zauzeto) {
        this.zauzeto = zauzeto;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
    @Override
    public String toString(){
        return this.getRegistarskiBroj() + " ("+this.getNaziv() + ")";
    }


}

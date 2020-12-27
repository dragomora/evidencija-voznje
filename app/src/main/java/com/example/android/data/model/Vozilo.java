package com.example.android.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vozilo {

    private String id;
    @SerializedName("datumRegistracije")
    private Date datum_registracije;
    private Integer trenutnaKm;
    private String registarskiBroj;
    private String naziv;
    private Integer servisniInterval;
    private Integer poslednjiServisKm;
    private Integer predjeno_km;
    @SerializedName("gorivo_litre")
    private Integer ukupnoLitara;
    @SerializedName("gorivo_iznos")
    private Integer ukupanIznosLitara;
    private Boolean zauzeto;
    private List<Sum> sum;
    private boolean expanded;

    private Map<String,Object> additionalProperties = new HashMap<String,Object>();

    public Integer getPredjeno_km() {
        return predjeno_km;
    }

    public Integer getUkupnoLitara() {
        return ukupnoLitara;
    }

    public Integer getUkupanIznosLitara() {
        return ukupanIznosLitara;
    }

    public List<Sum> getSum() {
        return sum;
    }

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

    public Integer getTrenutnaKm() {
        return trenutnaKm;
    }

    public void setTrenutnaKm(Integer trenutnaKm) {
        this.trenutnaKm = trenutnaKm;
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

    public Integer getServisniInterval() { return servisniInterval; }

    public void setServisniInterval(Integer servisniInterval) { this.servisniInterval = servisniInterval; }

    public Integer getPoslednjiServisKm() { return poslednjiServisKm; }

    public void setPoslednjiServisKm(Integer poslednjiServisKm) { this.poslednjiServisKm = poslednjiServisKm; }

    public Boolean getZauzeto() {
        return zauzeto;
    }

    public void setZauzeto(Boolean zauzeto) {
        this.zauzeto = zauzeto;
    }

    public boolean isExpanded() { return expanded; }

    public void setExpanded(boolean expanded) { this.expanded = expanded; }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public Vozilo() {
    }

    public Vozilo(String id, Date datum_registracije, Integer trenutnaKm, String registarskiBroj, String naziv, Boolean zauzeto, Integer servisniInterval,Integer poslednjiServisKm, List <Sum> sum) {
        this.id = id;
        this.datum_registracije = datum_registracije;
        this.trenutnaKm = trenutnaKm;
        this.registarskiBroj = registarskiBroj;
        this.naziv = naziv;
        this.zauzeto = zauzeto;
        this.servisniInterval = servisniInterval;
        this.poslednjiServisKm = poslednjiServisKm;
        this.sum = sum;
        this.expanded = false;
    }

    @Override
    public String toString(){
        return this.getRegistarskiBroj() + " ("+this.getNaziv() + ")";
    }


}

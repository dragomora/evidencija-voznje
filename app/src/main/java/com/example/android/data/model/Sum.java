package com.example.android.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Sum {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("registarskiBroj")
    @Expose
    private String registarskiBroj;
    @SerializedName("datumRegistracije")
    @Expose
    private Date datumRegistracije;
    @SerializedName("servisniInterval")
    @Expose
    private Integer servisniInterval;
    @SerializedName("poslednjiServisKm")
    @Expose
    private Integer poslednjiServisKm;
    @SerializedName("trenutnaKm")
    @Expose
    private Integer trenutnaKm;
    @SerializedName("predjenoKmOdServisa")
    @Expose
    private Integer predjenoKmOdServisa;
    @SerializedName("zauzeto")
    @Expose
    private Boolean zauzeto;
    @SerializedName("naziv")
    @Expose
    private String naziv;
    public Sum() {
    }

    public Sum(String id, String registarskiBroj, Date datumRegistracije, Integer servisniInterval, Integer poslednjiServisKm, Integer trenutnaKm, Integer predjenoKmOdServisa, Boolean zauzeto, String naziv) {
        super();
        this.id = id;
        this.registarskiBroj = registarskiBroj;
        this.datumRegistracije = datumRegistracije;
        this.servisniInterval = servisniInterval;
        this.poslednjiServisKm = poslednjiServisKm;
        this.trenutnaKm = trenutnaKm;
        this.predjenoKmOdServisa = predjenoKmOdServisa;
        this.zauzeto = zauzeto;
        this.naziv = naziv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public Date getDatumRegistracije() {
        return datumRegistracije;
    }

    public void setDatumRegistracije(Date datumRegistracije) {
        this.datumRegistracije = datumRegistracije;
    }

    public Integer getServisniInterval() {
        return servisniInterval;
    }

    public void setServisniInterval(Integer servisniInterval) {
        this.servisniInterval = servisniInterval;
    }

    public Integer getPoslednjiServisKm() {
        return poslednjiServisKm;
    }

    public void setPoslednjiServisKm(Integer poslednjiServisKm) {
        this.poslednjiServisKm = poslednjiServisKm;
    }

    public Integer getTrenutnaKm() {
        return trenutnaKm;
    }

    public void setTrenutnaKm(Integer trenutnaKm) {
        this.trenutnaKm = trenutnaKm;
    }

    public Integer getPredjenoKmOdServisa() {
        return predjenoKmOdServisa;
    }

    public void setPredjenoKmOdServisa(Integer predjenoKmOdServisa) {
        this.predjenoKmOdServisa = predjenoKmOdServisa;
    }

    public Boolean getZauzeto() {
        return zauzeto;
    }

    public void setZauzeto(Boolean zauzeto) {
        this.zauzeto = zauzeto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}

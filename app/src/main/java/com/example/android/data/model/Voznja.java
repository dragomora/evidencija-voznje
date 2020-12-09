package com.example.android.data.model;

import java.time.LocalDate;
import java.util.Date;

import androidx.core.app.RemoteInput;
import com.example.android.data.model.Korisnici;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;


public class Voznja {
    @SerializedName("_id")
    private String id;

    private Date pocetakVoznje;
    private Integer pocetnaKm;
    private Integer zavrsnaKm;
    private Integer predjenoKm;
    private Date krajVoznje;
    //@SerializedName("korisnik")
    @Expose
    private Korisnici korisnikId;
    @SerializedName("korisnik_id")
    private String korisnik_Id;
    private String voziloId;
    private SvrhaVoznje svrha;
    private StanjeVozila stanjeVozila;
    private String napomena;
    private String destinacija;
    private boolean pranje;

    public Voznja(String id, Date pocetakVoznje, Integer pocetnaKm, Integer zavrsnaKm, Integer predjenoKm, Date krajVoznje,Korisnici korisnikId, String voziloId, String napomena, boolean pranje) {
        this.id = id;
        this.pocetakVoznje = pocetakVoznje;
        this.pocetnaKm = pocetnaKm;
        this.zavrsnaKm = zavrsnaKm;
        this.predjenoKm = predjenoKm;
        this.krajVoznje = krajVoznje;
        this.korisnikId = korisnikId;
        this.voziloId = voziloId;
        this.napomena = napomena;
        this.pranje = pranje;
    }
    private Voznja voznja;
    public Voznja() {
    }
    public Voznja getVoznja(){
        return voznja;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPocetakVoznje() {
        return pocetakVoznje;
    }

    public void setPocetakVoznje(Date pocetakVoznje) {
        this.pocetakVoznje = pocetakVoznje;
    }

    public Integer getPocetnaKm() {
        return pocetnaKm;
    }

    public void setPocetnaKm(Integer pocetnaKm) {
        this.pocetnaKm = pocetnaKm;
    }

    public Integer getZavrsnaKm() {
        return zavrsnaKm;
    }

    public void setZavrsnaKm(Integer zavrsnaKm) {
        this.zavrsnaKm = zavrsnaKm;
    }

    public Integer getPredjenoKm() {
        return predjenoKm;
    }

    public void setPredjenoKm(Integer predjenoKm) {
        this.predjenoKm = predjenoKm;
    }

    public Date getKrajVoznje() {
        return krajVoznje;
    }

    public void setKrajVoznje(Date krajVoznje) {
        this.krajVoznje = krajVoznje;
    }

    public Korisnici getKorisnikId() {
        return korisnikId;
    }

    public String getVoziloId() {
        return voziloId;
    }

    public void setVoziloId(String voziloId) {
        this.voziloId = voziloId;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public boolean isPranje() {
        return pranje;
    }

    public void setPranje(boolean pranje) {
        this.pranje = pranje;
    }

    public String getKorisnik_Id() {
        return korisnik_Id;
    }

    public void setKorisnik_Id(String korisnik_Id) {
        this.korisnik_Id = korisnik_Id;
    }

    public Voznja(Date pocetakVoznje, Integer pocetnaKm, Integer zavrsnaKm, Integer predjenoKm, Date krajVoznje, String korisnik_Id, String voziloId, String napomena,String destinacija, SvrhaVoznje svrha, StanjeVozila stanjeVozila, boolean pranje) {
        this.pocetakVoznje = pocetakVoznje;
        this.pocetnaKm = pocetnaKm;
        this.zavrsnaKm = zavrsnaKm;
        this.predjenoKm = predjenoKm;
        this.krajVoznje = krajVoznje;
        this.korisnik_Id = korisnik_Id;
        this.voziloId = voziloId;
        this.napomena = napomena;
        this.destinacija = destinacija;
        this.svrha = svrha;
        this.stanjeVozila = stanjeVozila;
        this.pranje = pranje;
    }

}

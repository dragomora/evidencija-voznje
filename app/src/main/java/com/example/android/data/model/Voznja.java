package com.example.android.data.model;

import java.util.Date;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Voznja {
    @SerializedName("_id")
    private String id;

    private Date pocetakVoznje;
    private Integer pocetnaKm;
    private Integer zavrsnaKm;
    private Integer predjenoKm;
    private Date krajVoznje;
    @Expose
    @SerializedName("korisnik")
    private Korisnici korisnikId;
    @SerializedName("korisnik_id")
    private String korisnik_Id;
    @SerializedName("vozilo")
    private Vozilo vozilo;
    private String voziloId;
    private SvrhaVoznje svrha;
    private Gorivo gorivo;
    private String gorivoId;

    private StanjeVozila stanjeVozila;
    private String napomena;
    private String destinacija;
    private boolean pranje;
    private boolean expanded;

    public Voznja(String id, Date pocetakVoznje, Integer pocetnaKm, Integer zavrsnaKm, Integer predjenoKm, Date krajVoznje,Korisnici korisnikId, Vozilo vozilo,Gorivo gorivo, String napomena, boolean pranje) {
        this.id = id;
        this.pocetakVoznje = pocetakVoznje;
        this.pocetnaKm = pocetnaKm;
        this.zavrsnaKm = zavrsnaKm;
        this.predjenoKm = predjenoKm;
        this.krajVoznje = krajVoznje;
        this.korisnikId = korisnikId;
        this.vozilo = vozilo;
        this.voziloId = voziloId;
        this.gorivo = gorivo;
        this.napomena = napomena;
        this.pranje = pranje;
        this.expanded = false;
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

    public String getDestinacija() {return destinacija;}

    public void setDestinacija(String destinacija) {this.destinacija = destinacija;}

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

    public Vozilo getVozilo() { return vozilo; }

    public void setVozilo(Vozilo vozilo) { this.vozilo = vozilo; }

    public void setVoziloId(String voziloId) {
        this.voziloId = voziloId;
    }

    public Gorivo getGorivo() { return gorivo; }

    public void setGorivo(Gorivo gorivo) { this.gorivo = gorivo; }

    public String getGorivoId() { return gorivoId; }

    public void setGorivoId(String gorivoId) { this.gorivoId = gorivoId; }

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

    public SvrhaVoznje getSvrha() { return svrha; }

    public void setSvrha(SvrhaVoznje svrha) { this.svrha = svrha; }

    public void setKorisnikId(Korisnici korisnikId) { this.korisnikId = korisnikId; }

    public StanjeVozila getStanjeVozila() {return stanjeVozila; }

    public void setStanjeVozila(StanjeVozila stanjeVozila) { this.stanjeVozila = stanjeVozila; }

    public boolean isExpanded() { return expanded; }

    public void setExpanded(boolean expanded) { this.expanded = expanded; }

    public Voznja(Date pocetakVoznje, Integer pocetnaKm, Integer zavrsnaKm, Integer predjenoKm, Date krajVoznje, String korisnik_Id, String voziloId, String napomena, String destinacija, SvrhaVoznje svrha, StanjeVozila stanjeVozila, boolean pranje) {
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

    public Voznja(Integer zavrsnaKm, Integer predjenoKm, Date krajVoznje, StanjeVozila stanjeVozila, String napomena, String destinacija, boolean pranje) {
        this.zavrsnaKm = zavrsnaKm;
        this.predjenoKm = predjenoKm;
        this.krajVoznje = krajVoznje;
        this.stanjeVozila = stanjeVozila;
        this.napomena = napomena;
        this.destinacija = destinacija;
        this.pranje = pranje;
    }


}

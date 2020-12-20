package com.example.android.data.model;

import java.util.HashMap;
import java.util.Map;

public class Korisnici {

    private String id;
    private String ime;
    private String prezime;
    private Boolean admin;
    private String korisnickoIme;
    private String lozinka;
    private String otvorenaVoznjaId;
    private boolean expanded;

    private Map<String,Object> additionalProperties = new HashMap<String,Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public Korisnici(String id, String ime, String prezime, Boolean admin, String korisnickoIme, String lozinka, String otvorenaVoznjaId) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.admin = admin;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.otvorenaVoznjaId = otvorenaVoznjaId;
        this.expanded = false;
    }

    public String getOtvorenaVoznjaId() {
        return otvorenaVoznjaId;
    }

    public void setOtvorenaVoznjaId(String otvorenaVoznjaId) {
        this.otvorenaVoznjaId = otvorenaVoznjaId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}

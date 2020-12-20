package com.example.android.data.model;

public class Gorivo {
    private String id;
    private Integer litre;
    private Integer iznos;
    private String vrsta;
    private String voznjaId;

    public Gorivo() {
    }

    public Gorivo(String id,Integer litre, Integer iznos,   String vrsta, String voznjaId) {
        this.id = id;
        this.litre = litre;
        this.iznos = iznos;
        this.vrsta = vrsta;
        this.voznjaId = voznjaId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLitre() {
        return litre;
    }

    public void setLitre(Integer litre) {
        this.litre = litre;
    }

    public Integer getIznos() {
        return iznos;
    }

    public void setIznos(Integer iznos) {
        this.iznos = iznos;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getVoznjaId() {
        return voznjaId;
    }

    public void setVoznjaId(String voznjaId) {
        this.voznjaId = voznjaId;
    }

    public Gorivo(Integer litre, Integer iznos, String vrsta, String voznjaId) {
        this.voznjaId = voznjaId;
        this.litre = litre;
        this.iznos = iznos;
        this.vrsta = vrsta;
    }



}

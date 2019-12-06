package com.myapp.ayatayatsukses.Model;

public class Biodata {
    private int id;
    private String namaDosen;
    private String namaAslab;
    private String namaKelompok;

    public Biodata(int id, String namaDosen, String namaAslab, String namaKelompok) {
        this.id = id;
        this.namaDosen = namaDosen;
        this.namaAslab = namaAslab;
        this.namaKelompok = namaKelompok;
    }
    public Biodata(String namaDosen, String namaAslab, String namaKelompok) {
        this.namaDosen = namaDosen;
        this.namaAslab = namaAslab;
        this.namaKelompok = namaKelompok;
    }

    public Biodata() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public String getNamaAslab() {
        return namaAslab;
    }

    public void setNamaAslab(String namaAslab) {
        this.namaAslab = namaAslab;
    }

    public String getNamaKelompok() {
        return namaKelompok;
    }

    public void setNamaKelompok(String namaKelompok) {
        this.namaKelompok = namaKelompok;
    }
}

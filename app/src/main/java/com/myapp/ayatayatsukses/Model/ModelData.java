package com.myapp.ayatayatsukses.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelData {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nama")
    @Expose
    private String nm_ayat;

    @SerializedName("ayat")
    @Expose
    private String isi_ayat;

    @SerializedName("keterangan")
    @Expose
    private String arti_ayat;

    public static final String idAyat = "id";
    public static final String namaAyat = "nama";
    public static final String isiAyat = "ayat";
    public static final String artiAyat = "keterangan";

    public ModelData(String id, String nm_ayat, String isi_ayat, String arti_ayat) {
        this.id = id;
        this.nm_ayat = nm_ayat;
        this.isi_ayat = isi_ayat;
        this.arti_ayat = arti_ayat;
    }

    public ModelData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNm_ayat() {
        return nm_ayat;
    }

    public void setNm_ayat(String nm_ayat) {
        this.nm_ayat = nm_ayat;
    }

    public String getIsi_ayat() {
        return isi_ayat;
    }

    public void setIsi_ayat(String isi_ayat) {
        this.isi_ayat = isi_ayat;
    }

    public String getArti_ayat() {
        return arti_ayat;
    }

    public void setArti_ayat(String arti_ayat) {
        this.arti_ayat = arti_ayat;
    }

    public static String getIdAyat() {
        return idAyat;
    }

    public static String getNamaAyat() {
        return namaAyat;
    }

    public static String getIsiAyat() {
        return isiAyat;
    }

    public static String getArtiAyat() {
        return artiAyat;
    }
}

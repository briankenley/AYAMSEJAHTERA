package edu.uph.ayamsejahtera.model;

import java.util.Date;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class JadwalVaksin extends RealmObject {

    @PrimaryKey
    private int idVaksin;

    @Required
    private String idKandang;

    @Required
    private Date tanggal;

    private String waktu;
    private String jenisVaksin;

    public JadwalVaksin() {}

    // Getters and Setters disesuaikan
    public int getIdVaksin() {
        return idVaksin;
    }

    public void setIdVaksin(int idVaksin) {
        this.idVaksin = idVaksin;
    }

    public String getIdKandang() {
        return idKandang;
    }

    public void setIdKandang(String idKandang) {
        this.idKandang = idKandang;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getJenisVaksin() {
        return jenisVaksin;
    }

    public void setJenisVaksin(String jenisVaksin) {
        this.jenisVaksin = jenisVaksin;
    }
}
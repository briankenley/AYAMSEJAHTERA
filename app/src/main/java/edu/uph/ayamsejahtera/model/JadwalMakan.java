package edu.uph.ayamsejahtera.model;

import java.util.Date;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class JadwalMakan extends RealmObject {

    @PrimaryKey
    private String idJadwalMakan; //
    private Date tanggal;
    private RealmList<String> waktuMakan;
    private double jumlahPorsi;

    // Diperlukan constructor kosong oleh Realm
    public JadwalMakan() {
    }

    // Getters and Setters
    public String getIdJadwalMakan() {
        return idJadwalMakan;
    }

    public void setIdJadwalMakan(String idJadwalMakan) { // <-- PERBAIKAN
        this.idJadwalMakan = idJadwalMakan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public RealmList<String> getWaktuMakan() {
        return waktuMakan;
    }

    public void setWaktuMakan(RealmList<String> waktuMakan) {
        this.waktuMakan = waktuMakan;
    }

    public double getJumlahPorsi() {
        return jumlahPorsi;
    }

    public void setJumlahPorsi(double jumlahPorsi) {
        this.jumlahPorsi = jumlahPorsi;
    }
}
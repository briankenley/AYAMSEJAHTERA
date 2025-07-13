package edu.uph.ayamsejahtera.model;

import java.util.Date;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class JadwalMakan extends RealmObject {

    @PrimaryKey
    private int idJadwal;
    private Date tanggal;
    private RealmList<String> waktuMakan;
    private double jumlahPorsi;

    // Constructor kosong
    public JadwalMakan() {
    }

    // Getters and Setters yang sudah disesuaikan
    public int getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(int idJadwal) {
        this.idJadwal = idJadwal;
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
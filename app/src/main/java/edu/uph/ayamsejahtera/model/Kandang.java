package edu.uph.ayamsejahtera.model;

import android.widget.Spinner;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Kandang extends RealmObject {
    @PrimaryKey
    private  int kandangID;
    private String namaKandang;
    private Double kapasitas,jumlahAyam;

    private Spinner status;

    public Kandang() {
    }

    public Kandang(String namaKandang, Double kapasitas, Double jumlahAyam, Spinner status, int kandangID) {
        this.namaKandang = namaKandang;
        this.kapasitas = kapasitas;
        this.jumlahAyam = jumlahAyam;
        this.status = status;
        this.kandangID = kandangID;
    }

    public String getNamaKandang() {
        return namaKandang;
    }

    public void setNamaKandang(String namaKandang) {
        this.namaKandang = namaKandang;
    }

    public Double getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(Double kapasitas) {
        this.kapasitas = kapasitas;
    }

    public Double getJumlahAyam() {
        return jumlahAyam;
    }

    public void setJumlahAyam(Double jumlahAyam) {
        this.jumlahAyam = jumlahAyam;
    }

    public Spinner getStatus() {
        return status;
    }

    public void setStatus(Spinner status) {
        this.status = status;
    }

    public int getKandangID() {
        return kandangID;
    }

    public void setKandangID(int kandangID) {
        this.kandangID = kandangID;
    }

    @Override
    public String toString() {
        return "Kandang{" +
                "namaKandang='" + namaKandang + '\'' +
                ", kandangID=" + kandangID + '\'' +
                ", kapasitas='" + kapasitas + '\'' +
                ", jumlahAyam='" + jumlahAyam + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
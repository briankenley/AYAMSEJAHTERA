package edu.uph.ayamsejahtera.model;

import android.widget.Spinner;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Kandang extends RealmObject {
    @PrimaryKey
    private  int kandangID;
    private String namaKandang;
    private Double kapasitas,jumlahAyam;

    private String status;

    public Kandang() {
        // Default constructor required by Realm
    }

    public Kandang(String namaKandang, Double kapasitas, Double jumlahAyam, Spinner statusSpinner, int kandangID) {
        this.namaKandang = namaKandang;
        this.kapasitas = kapasitas;
        this.jumlahAyam = jumlahAyam;
        if (statusSpinner != null && statusSpinner.getSelectedItem() != null) {
            this.status = statusSpinner.getSelectedItem().toString();
        } else {
            this.status = null; // Or set a default status string if appropriate
        }
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getKandangID() {
        return kandangID;
    }

    public void setKandangID(int kandangID) {
        this.kandangID = kandangID;
    }

}

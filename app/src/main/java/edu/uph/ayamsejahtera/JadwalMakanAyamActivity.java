package edu.uph.ayamsejahtera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.uph.ayamsejahtera.adapter.JadwalMakanAdapter;
import edu.uph.ayamsejahtera.model.JadwalMakan;
import io.realm.Realm;
import io.realm.RealmResults;

public class JadwalMakanAyamActivity extends AppCompatActivity {

    private Realm realm;
    private ListView lsvJadwalMakanAyam;
    private JadwalMakanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_makan_ayam);

        realm = Realm.getDefaultInstance();

        lsvJadwalMakanAyam = findViewById(R.id.lsvJadwalMakanAyam);
        Button buttonTambah = findViewById(R.id.btnTambah);

        buttonTambah.setOnClickListener(v -> {
            Intent intent = new Intent(JadwalMakanAyamActivity.this, TambahJadwalMakanAyamActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.back_arrow).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadJadwalMakan();
    }

    private void loadJadwalMakan() {
        RealmResults<JadwalMakan> jadwalMakanResults = realm.where(JadwalMakan.class).findAll();
        // Menggunakan RealmResults secara langsung lebih efisien
        adapter = new JadwalMakanAdapter(this, realm.copyFromRealm(jadwalMakanResults));
        lsvJadwalMakanAyam.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}
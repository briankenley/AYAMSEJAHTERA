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
        loadJadwalMakan();
    }

    private void loadJadwalMakan() {
        RealmResults<JadwalMakan> jadwalMakanResults = realm.where(JadwalMakan.class).findAll();
        ArrayList<JadwalMakan> list = new ArrayList<>(realm.copyFromRealm(jadwalMakanResults));
        adapter = new JadwalMakanAdapter(this, list);
        lsvJadwalMakanAyam.setAdapter(adapter);
        jadwalMakanResults.addChangeListener(results -> adapter.notifyDataSetChanged());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        }
    }
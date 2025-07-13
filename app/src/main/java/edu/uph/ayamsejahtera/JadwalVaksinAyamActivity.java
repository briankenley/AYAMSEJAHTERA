package edu.uph.ayamsejahtera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.uph.ayamsejahtera.adapter.JadwalVaksinAdapter;
import edu.uph.ayamsejahtera.model.JadwalVaksin;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class JadwalVaksinAyamActivity extends AppCompatActivity {

    private Realm realm;
    private ListView lsvJadwalVaksin;
    private JadwalVaksinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_vaksin_ayam);

        findViewById(R.id.back_arrow).setOnClickListener(v -> finish());

        realm = Realm.getDefaultInstance();

        lsvJadwalVaksin = findViewById(R.id.lsvJadwalVaksinAyam);
        Button buttonTambah = findViewById(R.id.btnTambah);

        buttonTambah.setOnClickListener(v -> {
            Intent intent = new Intent(JadwalVaksinAyamActivity.this, TambahVaksinasiAyamActivity.class);
            startActivity(intent);
        });

        loadJadwalVaksin();
    }

    private void loadJadwalVaksin() {
        RealmResults<JadwalVaksin> jadwalVaksinResults = realm.where(JadwalVaksin.class)
                .sort("tanggal", Sort.DESCENDING)
                .findAll();

        ArrayList<JadwalVaksin> list = new ArrayList<>(realm.copyFromRealm(jadwalVaksinResults));
        adapter = new JadwalVaksinAdapter(this, list);
        lsvJadwalVaksin.setAdapter(adapter);

        jadwalVaksinResults.addChangeListener(results -> adapter.notifyDataSetChanged());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
package edu.uph.ayamsejahtera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

        realm = Realm.getDefaultInstance();

        lsvJadwalVaksin = findViewById(R.id.lsvJadwalVaksinAyam);
        Button buttonTambah = findViewById(R.id.btnTambah);

        // Listener untuk tombol tambah
        buttonTambah.setOnClickListener(v -> {
            Intent intent = new Intent(JadwalVaksinAyamActivity.this, TambahVaksinasiAyamActivity.class);
            startActivity(intent);
        });

        // Listener untuk tombol kembali
        findViewById(R.id.back_arrow).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Muat data setiap kali activity ini ditampilkan agar daftar selalu update
        loadJadwalVaksin();
    }

    private void loadJadwalVaksin() {
        // Ambil semua data JadwalVaksin dari Realm, urutkan berdasarkan tanggal terbaru
        RealmResults<JadwalVaksin> jadwalVaksinResults = realm.where(JadwalVaksin.class)
                .sort("tanggal", Sort.DESCENDING)
                .findAll();

        // Buat adapter dan set ke ListView
        adapter = new JadwalVaksinAdapter(this, realm.copyFromRealm(jadwalVaksinResults));
        lsvJadwalVaksin.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Tutup Realm
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}
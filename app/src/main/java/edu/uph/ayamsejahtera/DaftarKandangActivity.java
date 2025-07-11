package edu.uph.ayamsejahtera;

import android.content.Intent; // Impor Intent
import android.os.Bundle;
import android.view.View; // Impor View
import android.widget.Button; // Impor Button
import android.widget.ListView; // Impor ListView
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList; // Impor ArrayList
import edu.uph.ayamsejahtera.adapter.KandangAdapter; // Impor Adapter Anda
import edu.uph.ayamsejahtera.model.Kandang; // Impor Model Anda
import io.realm.Realm; // Impor Realm
import io.realm.RealmResults; // Impor RealmResults

public class DaftarKandangActivity extends AppCompatActivity {

    private Button btnTambah;
    private ListView lsvDaftarKandang;
    private Realm realm;
    private KandangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        findViewById(R.id.back_arrow).setOnClickListener(v -> finish());

        super.onCreate(savedInstanceState);
        // EdgeToEdge dan WindowInsets listener biarkan seperti semula
        setContentView(R.layout.activity_daftar_kandang);

        realm = Realm.getDefaultInstance(); // Inisialisasi Realm

        btnTambah = findViewById(R.id.btnTambah); // Ganti ID jika berbeda di XML Anda
        lsvDaftarKandang = findViewById(R.id.lsvDaftarKandang);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke TambahKandangActivity
                Intent intent = new Intent(DaftarKandangActivity.this, TambahKandangActivity.class);
                startActivity(intent);
            }
        });

        // Panggil method untuk menampilkan data
        loadKandangData();
    }

    private void loadKandangData() {
        RealmResults<Kandang> kandangResults = realm.where(Kandang.class).findAll();
        ArrayList<Kandang> kandangList = new ArrayList<>(realm.copyFromRealm(kandangResults));
        adapter = new KandangAdapter(this, kandangList);
        lsvDaftarKandang.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Muat ulang data setiap kali activity ini kembali ditampilkan
        // untuk merefleksikan data baru atau perubahan
        loadKandangData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Tutup instance Realm untuk menghindari memory leak
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}
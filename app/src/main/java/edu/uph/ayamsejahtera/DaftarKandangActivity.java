package edu.uph.ayamsejahtera;

import android.content.Intent; // Impor Intent
import android.os.Bundle;
import android.view.Gravity;
import android.view.View; // Impor View
import android.widget.Button; // Impor Button
import android.widget.ImageView;
import android.widget.ListView; // Impor ListView
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList; // Impor ArrayList
import edu.uph.ayamsejahtera.adapter.KandangAdapter; // Impor Adapter Anda
import edu.uph.ayamsejahtera.model.Kandang; // Impor Model Anda
import io.realm.Realm; // Impor Realm
import io.realm.RealmResults; // Impor RealmResults

public class DaftarKandangActivity extends AppCompatActivity {

    private Button btnTambah;

    private ImageView edit_icon_1;
    private ListView lsvDaftarKandang;
    private Realm realm;
    private KandangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // EdgeToEdge dan WindowInsets listener biarkan seperti semula
        setContentView(R.layout.activity_daftar_kandang);


        realm = Realm.getDefaultInstance();

        btnTambah = findViewById(R.id.btnTambah);
        lsvDaftarKandang = findViewById(R.id.lsvDaftarKandang);

        btnTambah.setOnClickListener(v -> {
            Intent intent = new Intent(DaftarKandangActivity.this, TambahKandangActivity.class);
            startActivity(intent);
        });

        // Panggil method untuk menampilkan data awal
        loadKandangData();
    }


    private void loadKandangData() {
        RealmResults<Kandang> kandangResults = realm.where(Kandang.class).findAll();
        ArrayList<Kandang> kandangList = new ArrayList<>(realm.copyFromRealm(kandangResults)); // Data disalin

        adapter = new KandangAdapter(this, kandangList);
        lsvDaftarKandang.setAdapter(adapter);
        kandangResults.addChangeListener(kandang -> adapter.notifyDataSetChanged());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
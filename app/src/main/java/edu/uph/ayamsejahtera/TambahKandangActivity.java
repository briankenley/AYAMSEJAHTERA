package edu.uph.ayamsejahtera;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils; // Impor TextUtils
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.uph.ayamsejahtera.model.Kandang;
import io.realm.Realm;

public class TambahKandangActivity extends AppCompatActivity {
    EditText edtNamaKandang, edtKapasitas, edtJumlahAyam, edtIdKandang;
    Button btnSimpan;
    Spinner spinnerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kandang);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tmbhKandang), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNamaKandang = findViewById(R.id.edtNamaKandang);
        edtIdKandang = findViewById(R.id.edtIdKandang);
        btnSimpan = findViewById(R.id.btnSimpan);
        edtKapasitas = findViewById(R.id.edtKapasitas);
        edtJumlahAyam = findViewById(R.id.edtJumlahAyam);
        spinnerStatus = findViewById(R.id.spinnerStatus);

        btnSimpan.setOnClickListener(v -> {
            //Validasi input sebelum menyimpan
            String namaKandangStr = edtNamaKandang.getText().toString().trim();
            String kapasitasStr = edtKapasitas.getText().toString().trim();
            String jumlahAyamStr = edtJumlahAyam.getText().toString().trim();

            if (namaKandangStr.isEmpty() || kapasitasStr.isEmpty() || jumlahAyamStr.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            } else {
                simpanData();
            }
        });
    }

    public void simpanData() {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(r -> {
                Number maxId = r.where(Kandang.class).max("kandangID");
                int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;

                Kandang kdg = r.createObject(Kandang.class, nextId);
                kdg.setNamaKandang(edtNamaKandang.getText().toString());
                kdg.setKapasitas(Double.parseDouble(edtKapasitas.getText().toString()));
                kdg.setJumlahAyam(Double.parseDouble(edtJumlahAyam.getText().toString()));
                kdg.setStatus(spinnerStatus.getSelectedItem().toString());
            });
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            finish();
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void bersihkanForm() {
        edtNamaKandang.setText("");
        edtKapasitas.setText("");
        edtJumlahAyam.setText("");
        if (spinnerStatus != null) { // Tambahkan pengecekan null untuk keamanan
            spinnerStatus.setSelection(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_daftarkandang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mBersihkanForm) {
            bersihkanForm();
            return true;
        } else if (item.getItemId() == R.id.mPengaturan) {
            // Logika untuk pengaturan
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
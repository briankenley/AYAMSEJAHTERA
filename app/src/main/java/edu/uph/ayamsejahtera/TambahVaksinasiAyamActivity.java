package edu.uph.ayamsejahtera;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.uph.ayamsejahtera.model.JadwalVaksin;
import io.realm.Realm;

public class TambahVaksinasiAyamActivity extends AppCompatActivity {

    private EditText editTextIdKandang, editTextTanggal, editTextWaktu;
    private Spinner spinnerJenisVaksin;
    private Button buttonSimpan;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_vaksinasi_ayam);

        realm = Realm.getDefaultInstance();

        editTextIdKandang = findViewById(R.id.editTextIdKandang);
        editTextTanggal = findViewById(R.id.editTextTanggal);
        editTextWaktu = findViewById(R.id.editTextWaktu);
        spinnerJenisVaksin = findViewById(R.id.spinnerJenisVaksin);
        buttonSimpan = findViewById(R.id.buttonSimpan);

        buttonSimpan.setOnClickListener(v -> simpanJadwal());
        findViewById(R.id.backButton).setOnClickListener(v -> finish());
    }

    private void simpanJadwal() {
        String idKandang = editTextIdKandang.getText().toString().trim();
        String tanggalStr = editTextTanggal.getText().toString().trim();
        String waktu = editTextWaktu.getText().toString().trim();
        String jenisVaksin = spinnerJenisVaksin.getSelectedItem().toString();

        if (idKandang.isEmpty() || tanggalStr.isEmpty() || waktu.isEmpty()) {
            Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        realm.executeTransaction(r -> {
            Number maxId = r.where(JadwalVaksin.class).max("idVaksin");
            int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;

            JadwalVaksin jadwal = r.createObject(JadwalVaksin.class, nextId);
            jadwal.setIdKandang(idKandang);
            jadwal.setWaktu(waktu);
            jadwal.setJenisVaksin(jenisVaksin);

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
                Date tanggal = sdf.parse(tanggalStr);
                jadwal.setTanggal(tanggal);
            } catch (ParseException e) {
                return;
            }
        });

        Toast.makeText(TambahVaksinasiAyamActivity.this, "Jadwal vaksin berhasil disimpan!", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void bersihkanForm() {
        editTextIdKandang.setText("");
        editTextTanggal.setText("");
        editTextWaktu.setText("");
        if (spinnerJenisVaksin != null) { // Tambahkan pengecekan null untuk keamanan
            spinnerJenisVaksin.setSelection(0);
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
        } 
        return super.onOptionsItemSelected(item);
    }
}
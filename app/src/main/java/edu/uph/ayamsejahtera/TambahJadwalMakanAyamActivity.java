package edu.uph.ayamsejahtera;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID; // Import UUID untuk generate ID unik

import edu.uph.ayamsejahtera.model.JadwalMakan; // <-- PERBAIKAN: Pastikan import benar
import io.realm.Realm;
import io.realm.RealmList;

public class TambahJadwalMakanAyamActivity extends AppCompatActivity {

    private EditText editTextIdKandang, editTextTanggal, editTextWaktu1, editTextWaktu2, editTextWaktu3, editTextWaktu4, editTextJumlahPorsi;
    private Button buttonSimpan;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jadwal_makan_ayam);

        realm = Realm.getDefaultInstance();

        editTextIdKandang = findViewById(R.id.editTextIdKandang);
        editTextTanggal = findViewById(R.id.editTextTanggal);
        editTextWaktu1 = findViewById(R.id.editTextWaktu1);
        editTextWaktu2 = findViewById(R.id.editTextWaktu2);
        editTextWaktu3 = findViewById(R.id.editTextWaktu3);
        editTextWaktu4 = findViewById(R.id.editTextWaktu4);
        editTextJumlahPorsi = findViewById(R.id.editTextJumlahPorsi);
        buttonSimpan = findViewById(R.id.buttonSimpan);

        buttonSimpan.setOnClickListener(v -> simpanJadwal());
        findViewById(R.id.backButton).setOnClickListener(v -> finish());
    }

    private void simpanJadwal() {

        String idJadwal = editTextIdKandang.getText().toString().trim();
        String tanggalStr = editTextTanggal.getText().toString().trim();
        String porsiStr = editTextJumlahPorsi.getText().toString().trim();

        if (idJadwal.isEmpty() || tanggalStr.isEmpty() || porsiStr.isEmpty()) {
            Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        RealmList<String> waktuMakanList = new RealmList<>();
        if (!editTextWaktu1.getText().toString().trim().isEmpty()) waktuMakanList.add(editTextWaktu1.getText().toString().trim());
        if (!editTextWaktu2.getText().toString().trim().isEmpty()) waktuMakanList.add(editTextWaktu2.getText().toString().trim());
        if (!editTextWaktu3.getText().toString().trim().isEmpty()) waktuMakanList.add(editTextWaktu3.getText().toString().trim());
        if (!editTextWaktu4.getText().toString().trim().isEmpty()) waktuMakanList.add(editTextWaktu4.getText().toString().trim());

        if (waktuMakanList.isEmpty()) {
            Toast.makeText(this, "Isi minimal satu waktu makan", Toast.LENGTH_SHORT).show();
            return;
        }

        realm.executeTransaction(r -> {
            JadwalMakan jadwal = r.createObject(JadwalMakan.class, idJadwal);

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
                Date tanggal = sdf.parse(tanggalStr);
                jadwal.setTanggal(tanggal);
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(this, "Format tanggal salah (dd/mm/yy)", Toast.LENGTH_SHORT).show();
                return;
            }

            jadwal.setWaktuMakan(waktuMakanList);
            jadwal.setJumlahPorsi(Double.parseDouble(porsiStr));

            Toast.makeText(TambahJadwalMakanAyamActivity.this, "Jadwal berhasil disimpan!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}
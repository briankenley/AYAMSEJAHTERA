package edu.uph.ayamsejahtera;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.uph.ayamsejahtera.model.Kandang;
import io.realm.Realm;

public class TambahKandangActivity extends AppCompatActivity {
    EditText edtNamaKandang,edtKapasitas,edtJumlahAyam;
    Button btnSimpan;

    Spinner spinnerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tambah_kandang);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tmbhKandang), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Realm.init(this);

        edtNamaKandang = findViewById(R.id.edtNamaKandang);
        btnSimpan = findViewById(R.id.btnSimpan);
        edtKapasitas = findViewById(R.id.edtKapasitas);
        edtJumlahAyam = findViewById(R.id.edtJumlahAyam);
        spinnerStatus = findViewById(R.id.spinnerStatus);


        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String usernamedef = getResources().getString(R.string.username_key);
        String username = sharedPref.getString(getString(R.string.username_key), usernamedef);

        edtNamaKandang.setText(username);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String namaKandang = edtNamaKandang.getText().toString();
                int kapasitas = Integer.parseInt(edtKapasitas.getText().toString());
                int jumlahAyam = Integer.parseInt(edtJumlahAyam.getText().toString());
                String status = spinnerStatus.getSelectedItem().toString();
                simpanData();

            }
        });
    }

    public void simpanData(){
        String namaKandang = edtNamaKandang.getText().toString();
        Double kapasitas=Double.parseDouble(edtKapasitas.getText().toString());
        Double jumlahAyam=Double.parseDouble(edtJumlahAyam.getText().toString());
        Spinner status = findViewById(R.id.spinnerStatus);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            Number maxId = r.where(Kandang.class).max("kandangID");
            int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
            Kandang kdg = r.createObject(Kandang.class, nextId);
            kdg.setNamaKandang(namaKandang);
            kdg.setKapasitas(kapasitas);
            kdg.setJumlahAyam(jumlahAyam);
            kdg.setStatus(status);
        });
        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show();
    }

    public void bersihkanForm(){
        edtNamaKandang.setText("");
        edtKapasitas.setText("");
        edtJumlahAyam.setText("");
        spinnerStatus.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_daftarkandang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        if(item.getItemId()==R.id.mBersihkanForm){
            bersihkanForm();
            return true;
        }else if(item.getItemId()==R.id.mPengaturan){

            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}
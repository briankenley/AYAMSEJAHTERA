package edu.uph.ayamsejahtera;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.realm.Realm;

public class TambahKandangActivity extends AppCompatActivity {
    EditText edtNamaKandang,edtIdKandang,edtKapasitas,edtJumlahAyam;
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
        edtIdKandang = findViewById(R.id.edtIdKandang);
        btnSimpan = findViewById(R.id.btnSimpan);
        edtKapasitas = findViewById(R.id.edtKapasitas);
        edtJumlahAyam = findViewById(R.id.edtJumlahAyam);
        spinnerStatus = findViewById(R.id.spinnerStatus);


        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String usernamedef = getResources().getString(R.string.username_key);
        String username = sharedPref.getString(getString(R.string.username_key), usernamedef);

        edtNama.setText(username);
        edtProdi.setText(getIntent().getStringExtra("prodi").toString());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nama = edtNama.getText().toString();
                String prodi = edtProdi.getText().toString();
                String fakultas = "Fakultas Teknologi Informasi";
                int nilaimobile=Integer.parseInt(edtMobile.getText().toString());
                int nilaibisnis=Integer.parseInt(edtBisnis.getText().toString());
                String jenisKelamin="";
                if(rdbPria.isChecked()) jenisKelamin=rdbPria.getText().toString();
                else if(rdbWanita.isChecked()) jenisKelamin=rdbWanita.getText().toString();
                String hobi="";
                if(ckbBasket.isChecked()) hobi+=ckbBasket.getText().toString();
                if(ckbMasak.isChecked()) hobi+=","+ckbMasak.getText().toString();
                txvHasil.setText(toUpperCase(nama) +"\n Jenis Kelamin "+jenisKelamin
                        +"\n Hobi "+hobi
                        + "\n Program Studi " + toUpperCase(prodi)
                        +"\n"+ toUpperCase(cekFakultas(prodi))+"\n IPK "+String.valueOf(hitungIPK(nilaibisnis,nilaimobile)));
                simpanData();

            }
        });
    }

    public void simpanData(){
        String nama = edtNama.getText().toString();
        String prodi = edtProdi.getText().toString();
        String fakultas = "Fakultas Teknologi Informasi";
        Double nilaimobile=Double.parseDouble(edtMobile.getText().toString());
        Double nilaibisnis=Double.parseDouble(edtBisnis.getText().toString());
        String jenisKelamin="-";
        if(rdbPria.isChecked()) jenisKelamin=rdbPria.getText().toString();
        else if(rdbWanita.isChecked()) jenisKelamin=rdbWanita.getText().toString();
        String jk = jenisKelamin;
        String hobi="";
        if(ckbBasket.isChecked()) hobi+=ckbBasket.getText().toString();
        if(ckbMasak.isChecked()) hobi+=","+ckbMasak.getText().toString();
        String hobby = hobi;
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            Number maxId = r.where(Mahasiswa.class).max("studentID");
            int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
            Mahasiswa mhs = r.createObject(Mahasiswa.class, nextId);
            mhs.setNama(nama);
            mhs.setProdi(prodi);
            mhs.setJenisKelamin(jk);
            mhs.setNilaiBisnis(nilaibisnis);
            mhs.setNilaiMobile(nilaimobile);
            mhs.setHobi(hobby);
        });
        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show();
    }

    public double hitungIPK(int nilaiBisnis,int nilaiMobile){
        return (((getbobot(nilaiBisnis)*3)+(getbobot(nilaiMobile)*3))/6);
    }
    public double getbobot(int nilai){
        if(nilai>=90)
            return 4.0;
        else if(nilai>=80 && nilai<90)
            return 3.5;
        else if(nilai>=70 && nilai<80)
            return 3.0;
        else if(nilai>=60 && nilai<70)
            return 2.5;
        else if(nilai>=50 && nilai<60)
            return 2.0;
        else
            return 0.0;
    }
    public String cekFakultas(String prodi){
        if(prodi.toLowerCase().equals("sistem informasi") ||
                prodi.equals("si"))
            return "Fakultas Teknologi Informasi";
        else if (prodi.toLowerCase().equals("informatika")) {
            return "Fakultas Teknologi Informasi";
        }
        else if (prodi.toLowerCase().equals("akuntansi") ||
                prodi.toLowerCase().equals("manajemen")||
                prodi.toLowerCase().equals("perhotelan")) {
            return "Fakultas Ekonomi dan Bisnis";
        }
        else if (prodi.toLowerCase().equals("hukum")) {
            return "Fakultas Hukum";
        }
        else
            return "Fakultas Tidak Ditemukan";
    }

    public String toUpperCase(String str){
        return  str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public void bersihkanForm(){
        edtNama.setText("");
        edtProdi.setText("");
        edtBisnis.setText("");
        edtMobile.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profil, menu);
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
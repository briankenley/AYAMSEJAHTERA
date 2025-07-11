package edu.uph.ayamsejahtera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtUsername, edtPassword;
    TextView textViewRegister;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi Realm
        Realm.init(this);

        // [PERBAIKAN] Konfigurasi Realm disederhanakan tanpa .modules()
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("kandang.realm")
                .schemaVersion(1)
                // Baris .modules(new KandangModule()) dihapus.
                // Realm akan otomatis mendeteksi semua kelas model Anda.
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();

        // Tetapkan sebagai konfigurasi default untuk seluruh aplikasi
        Realm.setDefaultConfiguration(config);


        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();


        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textViewRegister = findViewById(R.id.textViewRegister);

        btnLogin.setOnClickListener(v -> Login());

        textViewRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
            startActivity(intent);
        });
    }

    public void Login(){
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Username dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        // Logika validasi login (contoh)
        // Anda perlu menyesuaikan ini dengan model User yang sudah dibuat
        try (Realm realm = Realm.getDefaultInstance()) {
            // Ganti edu.uph.ayamsejahtera.model.User.class dengan kelas User Anda jika berbeda
            edu.uph.ayamsejahtera.User user = realm.where(edu.uph.ayamsejahtera.User.class).equalTo("username", username).findFirst();

            if (user != null && user.getPassword().equals(password)) {
                // Login Berhasil
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("USERNAME_EXTRA", username);
                startActivity(intent);
                finish();
            } else {
                // Login Gagal
                Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

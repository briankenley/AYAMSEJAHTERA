package edu.uph.ayamsejahtera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class DaftarActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_daftar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        realm = Realm.getDefaultInstance();
        edtUsername = findViewById(R.id.editTextUserName);
        edtPassword = findViewById(R.id.editTextPassword);
        TextView textViewLogin = findViewById(R.id.textViewLogin);
        MaterialButton buttonDaftar = findViewById(R.id.buttonDaftar);

        buttonDaftar.setOnClickListener(v -> daftarAkun());

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void daftarAkun() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

        realm.executeTransaction(r -> {
            // Cek apakah username sudah ada
            User existingUser = r.where(User.class).equalTo("username", username).findFirst();
            if (existingUser == null) {
                // Jika belum ada, buat user baru
                User user = r.createObject(User.class, username);
                user.setPassword(password); // Di aplikasi nyata, password harus di-hash!

                Toast.makeText(DaftarActivity.this, "Pendaftaran berhasil!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DaftarActivity.this, LoginActivity.class));
                finish();
            } else {
                // Jika username sudah terdaftar
                Toast.makeText(DaftarActivity.this, "Username sudah digunakan!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
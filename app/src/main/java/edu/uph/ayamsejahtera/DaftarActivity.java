package edu.uph.ayamsejahtera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import android.widget.TextView;

public class DaftarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        TextView textViewLogin = findViewById(R.id.textViewLogin);
        MaterialButton buttonDaftar = findViewById(R.id.buttonDaftar);

        textViewLogin.setOnClickListener(v -> {
            // Kembali ke LoginActivity
            finish();
        });

        buttonDaftar.setOnClickListener(v -> {
            // Hanya tampilkan pesan, lalu kembali ke LoginActivity
            Toast.makeText(DaftarActivity.this, "Pendaftaran berhasil, silakan masuk!", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}

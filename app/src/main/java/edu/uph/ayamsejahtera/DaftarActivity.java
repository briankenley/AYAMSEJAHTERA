package edu.uph.ayamsejahtera;

import android.content.Intent; // <-- Tambahkan import ini
import android.os.Bundle;
import android.view.View; // <-- Tambahkan import ini
import android.widget.TextView; // <-- Tambahkan import ini
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class DaftarActivity extends AppCompatActivity {

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

        TextView textViewLogin = findViewById(R.id.textViewLogin);
        MaterialButton buttonDaftar = findViewById(R.id.buttonDaftar);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DaftarActivity.this, "Pendaftaran berhasil, silakan masuk!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
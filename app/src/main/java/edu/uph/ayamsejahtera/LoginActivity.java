package edu.uph.ayamsejahtera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtUsername, edtPassword;
    TextView textViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("ayamsejahtera.realm")
                .schemaVersion(1)
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        setContentView(R.layout.activity_login);

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

    public void Login() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        // Langsung masuk ke MainActivity tanpa verifikasi
        Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("USERNAME_EXTRA", username); // Tetap kirim username untuk ditampilkan
        startActivity(intent);
        finish();
    }
}

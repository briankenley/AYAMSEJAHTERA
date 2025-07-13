package edu.uph.ayamsejahtera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtUsername, edtPassword;
    TextView textViewRegister;

    MaterialButton faceIdButton, googleButton;

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
        faceIdButton = findViewById(R.id.faceIdButton);
        googleButton = findViewById(R.id.googleButton);

        btnLogin.setOnClickListener(v -> Login());

        textViewRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
            startActivity(intent);
        });

        faceIdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Ini faceIdButton", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                toast.show();
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Ini googleButton", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                toast.show();
            }
        });
    }

    public void Login() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

        // Simpan username ke SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AplikasiLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USERNAME_KEY", username);
        editor.apply();

    }
}

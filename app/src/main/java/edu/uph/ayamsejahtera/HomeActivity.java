package edu.uph.ayamsejahtera;

import android.content.Intent; // <-- Tambahkan import ini
import android.os.Bundle;
import android.view.Gravity;
import android.view.View; // <-- Tambahkan import ini
import android.widget.TextView; // <-- Tambahkan import ini
import android.widget.Toast;

import com.google.android.material.button.MaterialButton; // <-- Tambahkan import ini

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MaterialButton emailButton = findViewById(R.id.emailButton);
        MaterialButton faceIdButton = findViewById(R.id.faceIdButton);
        MaterialButton googleButton = findViewById(R.id.googleButton);
        TextView loginLink = findViewById(R.id.loginLink);

        View.OnClickListener goToLoginListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };

        faceIdButton = findViewById(R.id.faceIdButton);
        faceIdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Ini faceIdButton", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                toast.show();
            }
        });

        googleButton = findViewById(R.id.googleButton);
        googleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Ini googleButton", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                toast.show();
            }
        });

        emailButton.setOnClickListener(goToLoginListener);

    }
}
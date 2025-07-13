package edu.uph.ayamsejahtera;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment; // Pastikan import ini benar
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.uph.ayamsejahtera.R;
import edu.uph.ayamsejahtera.databinding.ActivityMainBinding;
import edu.uph.ayamsejahtera.ui.utama.UtamaViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hapus semua kode pemeriksaan SharedPreferences

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String username = getIntent().getStringExtra("USERNAME_EXTRA");
        if (username == null) {
            username = "Pengguna"; // Nilai default jika terjadi kesalahan
        }

        Bundle startArgs = new Bundle();
        startArgs.putString("username_arg", username);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);
        NavController navController = navHostFragment.getNavController();

        navController.setGraph(R.navigation.mobile_navigation, startArgs);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_utama, R.id.navigation_deteksi_suhu, R.id.navigation_akun)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        String username = getIntent().getStringExtra("USERNAME_EXTRA");
        if (username != null) {
            UtamaViewModel utamaViewModel = new ViewModelProvider(this).get(UtamaViewModel.class);
            utamaViewModel.setUsername(username);
        }
    }
}
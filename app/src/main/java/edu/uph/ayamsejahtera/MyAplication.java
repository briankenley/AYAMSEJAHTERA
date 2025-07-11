package edu.uph.ayamsejahtera;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Pindahkan semua logika inisialisasi Realm ke sini.
        // Ini akan dijalankan hanya sekali saat aplikasi dimulai.
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("kandang.realm")
                .schemaVersion(1) // Anda bisa mulai lagi dari 1
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }
}

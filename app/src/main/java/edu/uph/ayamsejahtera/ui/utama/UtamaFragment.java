package edu.uph.ayamsejahtera.ui.utama;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import edu.uph.ayamsejahtera.DaftarKandangActivity;
import edu.uph.ayamsejahtera.JadwalMakanAyamActivity;
import edu.uph.ayamsejahtera.JadwalVaksinAyamActivity;
import edu.uph.ayamsejahtera.adapter.KandangAdapter;
import edu.uph.ayamsejahtera.databinding.FragmentUtamaBinding;
import edu.uph.ayamsejahtera.model.Kandang;
import io.realm.Realm;
import io.realm.RealmResults;

public class UtamaFragment extends Fragment {

    // Deklarasi variabel-variabel yang dibutuhkan
    private FragmentUtamaBinding binding;
    private Realm realm;
    private KandangAdapter kandangAdapter;
    private UtamaViewModel utamaViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inisialisasi Realm dan ViewModel dilakukan sekali di sini agar lebih bersih
        realm = Realm.getDefaultInstance();
        utamaViewModel = new ViewModelProvider(requireActivity()).get(UtamaViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUtamaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Memanggil metode untuk mengatur setiap bagian UI
        setupGreeting();
        setupKandangList();
        setupNavigationButtons();

        return root;
    }

    private void setupGreeting() {
        // Pastikan ID 'text_username' ada di file fragment_utama.xml Anda
        final TextView textViewUsername = binding.textUsername;

        // Mengambil data username dari ViewModel dan menampilkannya
        utamaViewModel.getUsername().observe(getViewLifecycleOwner(), name -> {
            if (name != null && !name.isEmpty()) {
                textViewUsername.setText("Hai, " + name);
            }
        });
    }

    private void setupKandangList() {
        ListView lsvDaftarKandang = binding.lsvDaftarKandang;

        RealmResults<Kandang> kandangResults = realm.where(Kandang.class).findAll();

        ArrayList<Kandang> list = new ArrayList<>(realm.copyFromRealm(kandangResults));
        kandangAdapter = new KandangAdapter(getActivity(), list);
        lsvDaftarKandang.setAdapter(kandangAdapter);

        // Listener untuk memperbarui daftar secara otomatis jika ada perubahan data
        kandangResults.addChangeListener(results -> {
            if (kandangAdapter != null) {
                ArrayList<Kandang> newList = new ArrayList<>(realm.copyFromRealm(results));
                kandangAdapter.clear();
                kandangAdapter.addAll(newList);
                kandangAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupNavigationButtons() {
        binding.btnDaftarKandang.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), DaftarKandangActivity.class)));

        binding.btnJadwalMakan.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), JadwalMakanAyamActivity.class)));

        binding.btnJadwalVaksin.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), JadwalVaksinAyamActivity.class)));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
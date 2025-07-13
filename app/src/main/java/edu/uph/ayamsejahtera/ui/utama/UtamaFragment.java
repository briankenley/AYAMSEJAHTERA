package edu.uph.ayamsejahtera.ui.utama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import edu.uph.ayamsejahtera.NotificationActivity;
import edu.uph.ayamsejahtera.R;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textViewWelcome = view.findViewById(R.id.text_username);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AplikasiLogin", Context.MODE_PRIVATE);

        // Ambil username yang tersimpan. "Guest" adalah nilai default jika data tidak ditemukan.
        String username = sharedPreferences.getString("USERNAME_KEY", "Guest");

        // Tampilkan username
        textViewWelcome.setText("Hai, " + username + "!");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUtamaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Memanggil metode untuk mengatur setiap bagian UI
        setupKandangList();
        setupNavigationButtons();

        return root;
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

        binding.btnNotification.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), NotificationActivity.class)));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
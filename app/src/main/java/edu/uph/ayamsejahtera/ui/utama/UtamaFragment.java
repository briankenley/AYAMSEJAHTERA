package edu.uph.ayamsejahtera.ui.utama;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.uph.ayamsejahtera.DaftarKandangActivity;
import edu.uph.ayamsejahtera.JadwalMakanAyamActivity;
import edu.uph.ayamsejahtera.JadwalVaksinAyamActivity;
import edu.uph.ayamsejahtera.databinding.FragmentUtamaBinding;

public class UtamaFragment extends Fragment {

    private FragmentUtamaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UtamaViewModel utamaViewModel =
                new ViewModelProvider(this).get(UtamaViewModel.class);

        binding = FragmentUtamaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        utamaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.btnDaftarKandang.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DaftarKandangActivity.class);
            startActivity(intent);
        });
        binding.btnJadwalMakan.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), JadwalMakanAyamActivity.class); //
            startActivity(intent);
        });
        binding.btnJadwalVaksin.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), JadwalVaksinAyamActivity.class); //
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
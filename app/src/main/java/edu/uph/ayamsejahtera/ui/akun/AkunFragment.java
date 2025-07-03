package edu.uph.ayamsejahtera.ui.akun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.uph.ayamsejahtera.databinding.FragmentAkunBinding;

public class AkunFragment extends Fragment {

    private FragmentAkunBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AkunViewModel akunViewModel =
                new ViewModelProvider(this).get(AkunViewModel.class);

        binding = FragmentAkunBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAkun;
        akunViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
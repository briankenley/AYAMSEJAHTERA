package edu.uph.ayamsejahtera.ui.deteksisuhu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.uph.ayamsejahtera.databinding.FragmentDeteksisuhuBinding;

public class DeteksiSuhuFragment extends Fragment {

    private FragmentDeteksisuhuBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DeteksiSuhuViewModel deteksiSuhuViewModel =
                new ViewModelProvider(this).get(DeteksiSuhuViewModel.class);

        binding = FragmentDeteksisuhuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        deteksiSuhuViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
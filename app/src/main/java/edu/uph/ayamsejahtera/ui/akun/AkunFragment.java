package edu.uph.ayamsejahtera.ui.akun;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import edu.uph.ayamsejahtera.DaftarKandangActivity;
import edu.uph.ayamsejahtera.HomeActivity;
import edu.uph.ayamsejahtera.R;
import edu.uph.ayamsejahtera.adapter.KandangAdapter;
import edu.uph.ayamsejahtera.model.Kandang;
import io.realm.Realm;
import io.realm.RealmResults;

// Assuming you have these classes defined elsewhere in your project
// import edu.uph.ayamsejahtera.R;
// import edu.uph.ayamsejahtera.adapter.KandangAdapter;
// import edu.uph.ayamsejahtera.model.Kandang;

public class AkunFragment extends Fragment {

    private RelativeLayout keluar;
    private TextView txvNamaKandang;
    private ListView numbersListView;
    private KandangAdapter numbersArrayAdapter;
    private Realm realm;
    private RealmResults<Kandang> results;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 1. Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_utama, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // 2. All view-related logic goes here
        super.onViewCreated(view, savedInstanceState);

        // Handle EdgeToEdge insets
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 3. Use `view.findViewById` to find your views
        keluar = view.findViewById(R.id.keluar);


        keluar.setOnClickListener(v -> toHome());

        setupRealmAndAdapter();
    }

    private void setupRealmAndAdapter() {
        realm = Realm.getDefaultInstance();
        final ArrayList<Kandang> arrayList = new ArrayList<>();
        results = realm.where(Kandang.class).findAll();

        if (results != null) {
            arrayList.addAll(realm.copyFromRealm(results));
        }

        // 4. Use `getContext()` or `getActivity()` for the adapter's context
        numbersArrayAdapter = new KandangAdapter(getContext(), arrayList);
        numbersListView.setAdapter(numbersArrayAdapter);

        // Listen for changes to update the UI
        results.addChangeListener(kandangResults -> numbersArrayAdapter.notifyDataSetChanged());
    }

    public void toHome() {
        // This should probably navigate to another Fragment or Activity
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

    // Example of navigating to another activity from a fragment
    public void toDaftarKandang(){
        // 5. Use `getActivity()` for the Intent context
        Intent intent = new Intent(getActivity(), DaftarKandangActivity.class);
        intent.putExtra("prodi","Sistem Informasi");
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (results != null) {
            results.removeAllChangeListeners();
        }
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}
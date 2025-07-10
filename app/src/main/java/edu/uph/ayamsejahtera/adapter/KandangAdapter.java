package edu.uph.ayamsejahtera.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.uph.ayamsejahtera.R;
import edu.uph.ayamsejahtera.model.Kandang;
import io.realm.Realm;

public class KandangAdapter extends ArrayAdapter<Kandang> {
    private Context mContext;
    private ArrayList<Kandang> dataSet;

    public KandangAdapter(@NonNull Context context, ArrayList<Kandang> list) {
        super(context, 0, list);
        this.mContext = context;
        this.dataSet = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.layoutdaftarkandang, parent, false);
        }

        Kandang currentKandang = dataSet.get(position);

        TextView tvNama = listItem.findViewById(R.id.kandang_1_title);
        // Tambahkan pengecekan null
        if (currentKandang.getNamaKandang() != null) {
            tvNama.setText(currentKandang.getNamaKandang());
        }

        TextView tvId = listItem.findViewById(R.id.value_id_1);
        tvId.setText("KDG" + String.format("%03d", currentKandang.getKandangID()));

        TextView tvKapasitas = listItem.findViewById(R.id.value_kapasitas_1);
        // Tambahkan pengecekan null
        if (currentKandang.getKapasitas() != null) {
            tvKapasitas.setText(String.valueOf(currentKandang.getKapasitas()));
        }

        TextView tvJumlah = listItem.findViewById(R.id.value_jumlah_1);
        // Tambahkan pengecekan null
        if (currentKandang.getJumlahAyam() != null) {
            tvJumlah.setText(String.valueOf(currentKandang.getJumlahAyam()));
        }

        TextView tvStatus = listItem.findViewById(R.id.value_status_1);
        // Tambahkan pengecekan null
        if (currentKandang.getStatus() != null) {
            tvStatus.setText(currentKandang.getStatus());
        }

        ImageView imvDelete = listItem.findViewById(R.id.delete_icon_1);
        imvDelete.setOnClickListener(v -> {
            deleteKandang(currentKandang, position);
        });

        return listItem;
    }

    private void deleteKandang(Kandang kandang, int position) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            Kandang itemToDelete = r.where(Kandang.class).equalTo("kandangID", kandang.getKandangID()).findFirst();
            if (itemToDelete != null) {
                itemToDelete.deleteFromRealm();
                // Hapus dari dataset adapter dan notifikasi perubahan
                dataSet.remove(position);
                notifyDataSetChanged();
            }
        });
        realm.close();
    }
}

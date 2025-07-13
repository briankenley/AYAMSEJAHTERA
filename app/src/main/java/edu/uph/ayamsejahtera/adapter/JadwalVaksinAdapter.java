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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import edu.uph.ayamsejahtera.R;
import edu.uph.ayamsejahtera.model.JadwalVaksin;
import io.realm.Realm;

public class JadwalVaksinAdapter extends ArrayAdapter<JadwalVaksin> {

    public JadwalVaksinAdapter(@NonNull Context context, @NonNull List<JadwalVaksin> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layoutjadwalvaksinayam, parent, false);
        }

        JadwalVaksin jadwal = getItem(position);

        TextView tvIdKandang = convertView.findViewById(R.id.kandang_1_title);
        TextView tvTanggal = convertView.findViewById(R.id.value_Tanggal_1);
        TextView tvWaktu = convertView.findViewById(R.id.value_waktuVaksin_1);
        TextView tvJenisVaksin = convertView.findViewById(R.id.value_jenisVaksin_1);
        ImageView imvDelete = convertView.findViewById(R.id.delete_icon_1);

        if (jadwal != null) {
            tvIdKandang.setText("Kandang ID: " + jadwal.getIdKandang());

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            tvTanggal.setText(sdf.format(jadwal.getTanggal()));

            tvWaktu.setText(jadwal.getWaktu());
            tvJenisVaksin.setText(jadwal.getJenisVaksin());

            imvDelete.setOnClickListener(v -> deleteJadwal(jadwal));
        }

        return convertView;
    }

    private void deleteJadwal(JadwalVaksin jadwal) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            JadwalVaksin itemToDelete = r.where(JadwalVaksin.class)
                    .equalTo("idVaksin", jadwal.getIdVaksin())
                    .findFirst();
            if (itemToDelete != null) {
                itemToDelete.deleteFromRealm();
                remove(jadwal);
                notifyDataSetChanged();
            }
        });
    }
}
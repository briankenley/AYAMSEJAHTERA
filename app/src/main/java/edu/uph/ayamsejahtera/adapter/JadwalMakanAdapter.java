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
import java.util.StringJoiner;

import edu.uph.ayamsejahtera.model.JadwalMakan;
import edu.uph.ayamsejahtera.R;
import io.realm.Realm;

public class JadwalMakanAdapter extends ArrayAdapter<JadwalMakan> {

    public JadwalMakanAdapter(@NonNull Context context, @NonNull List<JadwalMakan> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layoutjadwalmakanayam, parent, false);
        }

        JadwalMakan jadwalmakan = getItem(position);

        TextView tvTitle = convertView.findViewById(R.id.kandang_1_title);
        TextView tvTanggal = convertView.findViewById(R.id.value_Tanggal_1);
        TextView tvWaktuMakan = convertView.findViewById(R.id.value_waktuMakan_1);
        TextView tvJumlahPorsi = convertView.findViewById(R.id.value_jumlahPorsi_1);
        ImageView imvDelete = convertView.findViewById(R.id.delete_icon_1); // Pastikan ID ini benar

        if (jadwalmakan != null) {
            tvTitle.setText("Jadwal ID: " + jadwalmakan.getIdJadwal());

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            if (jadwalmakan.getTanggal() != null) {
                tvTanggal.setText(sdf.format(jadwalmakan.getTanggal()));
            }

            StringJoiner joiner = new StringJoiner(", ");
            if (jadwalmakan.getWaktuMakan() != null) {
                for (String waktu : jadwalmakan.getWaktuMakan()) {
                    joiner.add(waktu);
                }
            }
            tvWaktuMakan.setText(joiner.toString());

            tvJumlahPorsi.setText(String.format(Locale.getDefault(), "%.1f Kg", jadwalmakan.getJumlahPorsi()));

            imvDelete.setOnClickListener(v -> deleteJadwal(jadwalmakan));
        }

        return convertView;
    }

    private void deleteJadwal(JadwalMakan jadwal) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            JadwalMakan itemToDelete = r.where(JadwalMakan.class)
                    .equalTo("idJadwal", jadwal.getIdJadwal())
                    .findFirst();
            if (itemToDelete != null) {
                itemToDelete.deleteFromRealm();
                remove(jadwal);
                notifyDataSetChanged();
            }
        });
    }
}
package edu.uph.ayamsejahtera.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import edu.uph.ayamsejahtera.R;
import edu.uph.ayamsejahtera.model.JadwalVaksin;

public class JadwalVaksinAdapter extends ArrayAdapter<JadwalVaksin> {

    public JadwalVaksinAdapter(@NonNull Context context, @NonNull List<JadwalVaksin> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Gunakan layout item yang sudah Anda buat: layoutjadwalvaksinayam.xml
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layoutjadwalvaksinayam, parent, false);
        }

        // Dapatkan objek JadwalVaksin untuk posisi saat ini
        JadwalVaksin jadwal = getItem(position);

        // Hubungkan view dari layout
        TextView tvIdKandang = convertView.findViewById(R.id.kandang_1_title);
        TextView tvTanggal = convertView.findViewById(R.id.value_Tanggal_1);
        TextView tvWaktu = convertView.findViewById(R.id.value_waktuVaksin_1);
        TextView tvJenisVaksin = convertView.findViewById(R.id.value_jenisVaksin_1);

        if (jadwal != null) {
            // Set data ke view
            tvIdKandang.setText(jadwal.getIdKandang());

            // Format tanggal agar mudah dibaca
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            tvTanggal.setText(sdf.format(jadwal.getTanggal()));

            tvWaktu.setText(jadwal.getWaktu());
            tvJenisVaksin.setText(jadwal.getJenisVaksin());
        }

        return convertView;
    }
}
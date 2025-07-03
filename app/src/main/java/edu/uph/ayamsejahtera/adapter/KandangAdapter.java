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
    public KandangAdapter(@NonNull Context context, ArrayList<Kandang> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.layoutdaftarkandang, parent,
                    false);
        }

        // get the position of the view from the ArrayAdapter
        Kandang currentNumberPosition = getItem(position);

        //numbersImage.setImageResource(currentNumberPosition.getNumbersImageId());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.kandang_1_title);
        assert currentNumberPosition != null;
        textView1.setText(currentNumberPosition.getNamaKandang());

        // then according to the position of the view assign the desired TextView 2 for the same

        TextView textView2 = currentItemView.findViewById(R.id.value_id_1);
        textView2.setText(String.valueOf(currentNumberPosition.getKapasitas()));

        TextView textView3 = currentItemView.findViewById(R.id.value_kapasitas_1);
        textView3.setText(String.valueOf(currentNumberPosition.getKapasitas()));

        TextView textView4 = currentItemView.findViewById(R.id.value_jumlah_1);
        textView4.setText(String.valueOf(currentNumberPosition.getJumlahAyam()));

        ImageView imvdelete = currentItemView.findViewById(R.id.delete_icon_1);
        imvdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteKandang(currentNumberPosition.getKandangID());
            }
        });

        // then return the recyclable view
        return currentItemView;
    }
    private void deleteKandang(long id) {
        Realm realm = Realm.getDefaultInstance();
        Kandang mhs = realm.where(Kandang.class).equalTo("kandangID", id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mhs.deleteFromRealm();
                remove(mhs);
                notifyDataSetChanged();
            }
        });
    }
}

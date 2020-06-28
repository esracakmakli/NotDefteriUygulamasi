package com.example.notdefteriuygulamasi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotlarViewHolder extends RecyclerView.ViewHolder {
    TextView notBasligi,notAciklamasi,tarih;
    CardView cardView;
    FloatingActionButton sil;


    public NotlarViewHolder(@NonNull View itemView) {
        super(itemView);
        notBasligi=itemView.findViewById(R.id.BaslikText);
        notAciklamasi=itemView.findViewById(R.id.NotText);
        tarih=itemView.findViewById(R.id.TarihText);
        cardView=itemView.findViewById(R.id.Notlar_RecyclerView);
        sil=itemView.findViewById(R.id.NotSilFab);
    }
    public void Not(final Context context,Notlar notlar){
        itemView.setTag(notlar);
        notBasligi.setText(notlar.getNotBaslik());
        notAciklamasi.setText(notlar.getNotAciklama());
        tarih.setText(notlar.getNotTarih());
    }
}

package com.example.notdefteriuygulamasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NotlarAdapter extends RecyclerView.Adapter<NotlarViewHolder> implements View.OnClickListener {
    private ArrayList<Notlar> notlarArrayList;
    private LayoutInflater layoutInflater;
    private AppCompatActivity appCompatActivity;
    private MyListener myListener;
    FirebaseDatabase database;
    DatabaseReference reference;
    TextView notBaslik;
    TextView notAciklama;
    TextView notTarih;
    FloatingActionButton buton;

    public NotlarAdapter(AppCompatActivity appCompatActivity, MyListener myListener) {
        this.appCompatActivity = appCompatActivity;
        this.myListener = myListener;
        layoutInflater=appCompatActivity.getLayoutInflater();
        notlarArrayList=new ArrayList<>();
    }

    public ArrayList<Notlar> NotlarArrayList(){
        return notlarArrayList;
    }

    public interface MyListener{
        public void MyListener(Notlar notlar);
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() instanceof Notlar){
            Notlar notlar=(Notlar) view.getTag();
            myListener.MyListener(notlar);
        }
    }

    @NonNull
    @Override
    public NotlarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listView=layoutInflater.inflate(R.layout.notlar_recyclerview_oge,parent,false);
        listView.setOnClickListener(this);
        notBaslik=listView.findViewById((R.id.BaslikText));
        notAciklama=listView.findViewById((R.id.NotText));
        notTarih=listView.findViewById((R.id.TarihText));
        buton=listView.findViewById(R.id.NotSilFab);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
        return new NotlarViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotlarViewHolder notlarViewHolder, int position) {
        final Notlar not=notlarArrayList.get(position);
        notlarViewHolder.Not(appCompatActivity,notlarArrayList.get(position));
        notlarViewHolder.sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(notlarViewHolder.sil,"Silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reference.child("Notlar").child(not.getNotID()).removeValue();
                        notifyDataSetChanged();
                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notlarArrayList.size();
    }
    public  void search(ArrayList<Notlar> newList){
        notlarArrayList=new ArrayList<>();
        notlarArrayList.addAll(newList);
        notifyDataSetChanged();
    }

}

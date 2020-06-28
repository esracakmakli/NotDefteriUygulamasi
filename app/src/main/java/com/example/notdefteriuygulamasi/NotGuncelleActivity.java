package com.example.notdefteriuygulamasi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotGuncelleActivity extends AppCompatActivity {

    EditText baslik_duzenle,aciklama_duzenle;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_guncelle);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.toolbar));
        Intent intent=getIntent();
        String aciklama=intent.getStringExtra("aciklama_duzenle");
        String baslik=intent.getStringExtra("baslik_duzenle");
        String id=intent.getStringExtra("id_al");
        date=new Date();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("Notlar").child(id);
        baslik_duzenle=findViewById(R.id.BaslikDuzenleText);
        aciklama_duzenle=findViewById(R.id.AciklamaDuzenleText);
        baslik_duzenle.setText(baslik);
        aciklama_duzenle.setText(aciklama);

    }
    public void Guncelle(View view){
        String baslik=baslik_duzenle.getText().toString();
        String not=aciklama_duzenle.getText().toString();
        String format="dd-MMM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(format);
        String tarih=dateFormat.format(date);
        if(baslik_duzenle.getText().length()>0 && aciklama_duzenle.getText().length()>0){
            databaseReference.child("Baslik").setValue(baslik);
            databaseReference.child("Aciklama").setValue(not);
            databaseReference.child("Tarih").setValue(tarih);
            Toast.makeText(getApplicationContext(),"Not Güncellendi!",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),AnaEkranActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Boş bırakılamaz.",Toast.LENGTH_LONG);
        }

    }

}
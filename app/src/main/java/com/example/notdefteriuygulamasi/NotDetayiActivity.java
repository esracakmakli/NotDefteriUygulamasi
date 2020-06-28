package com.example.notdefteriuygulamasi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotDetayiActivity extends AppCompatActivity {

    EditText DetayBaslik,DetayAciklama;
    TextView DetayTarih;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FloatingActionButton guncelle;
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_detayi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.toolbar));
        guncelle=findViewById(R.id.NotDuzenleFab);
        DetayBaslik=(EditText) findViewById(R.id.DetayBaslikText);
        DetayAciklama=(EditText) findViewById(R.id.DetayAciklamaText);
        DetayTarih=(TextView) findViewById(R.id.DetayTarihText);
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        intent=getIntent();
        DetayBaslik.setText(intent.getStringExtra("baslik"));
        DetayAciklama.setText(intent.getStringExtra("aciklama"));
        DetayTarih.setText(intent.getStringExtra("tarih"));
    }

    public void NotDuzenleFab(View view){
        Intent i=new Intent(getApplicationContext(),NotGuncelleActivity.class);
        i.putExtra("aciklama_duzenle", DetayAciklama.getText().toString());
        i.putExtra("baslik_duzenle", DetayBaslik.getText().toString());
        i.putExtra("id_al", intent.getStringExtra("id"));
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent intent=new Intent(getApplicationContext(),AnaEkranActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
package com.example.notdefteriuygulamasi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class NotEkleActivity extends AppCompatActivity {

    EditText baslik;
    EditText aciklama;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    Date datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekle);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.toolbar));
        baslik=(EditText) findViewById(R.id.BaslikText);
        aciklama=(EditText) findViewById(R.id.AciklamaText);
        datetime=new Date();
        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();
    }
    public void NotKaydet(View view){
        if(aciklama.getText().length()>0){
            final UUID uuid=UUID.randomUUID();
            final String uuidString=uuid.toString();
            String format="dd-MMM-yyyy";
            SimpleDateFormat dateFormat=new SimpleDateFormat(format);
            String notbaslik=baslik.getText().toString();
            String not=aciklama.getText().toString();
            String tarih=dateFormat.format(datetime);
            myRef.child("Notlar").child(uuidString).child("Baslik").setValue(notbaslik);
            myRef.child("Notlar").child(uuidString).child("Aciklama").setValue(not);
            myRef.child("Notlar").child(uuidString).child("Tarih").setValue(tarih);
            myRef.child("Notlar").child(uuidString).child("Not Id").setValue(uuidString);
            Toast.makeText(getApplicationContext(),"Not Kaydedildi!",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),AnaEkranActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Açıklama giriniz.",Toast.LENGTH_LONG).show();
        }
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
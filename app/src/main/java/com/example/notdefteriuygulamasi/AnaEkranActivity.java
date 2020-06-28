package com.example.notdefteriuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AnaEkranActivity extends AppCompatActivity implements NotlarAdapter.MyListener {

    private NotlarAdapter notlarAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Notlar> notlarArrayList;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.toolbar));
        fab=findViewById(R.id.NotEkleFab);
        notlarAdapter = new NotlarAdapter(this, this);
        notlarArrayList = notlarAdapter.NotlarArrayList();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Notlar");
        recyclerView = findViewById(R.id.Notlar_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(notlarAdapter);
        NotlarArrayList(notlarArrayList);
    }
    private ArrayList<Notlar> NotlarArrayList(final ArrayList<Notlar> notlarArrayList) {
         reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notlarArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();
                    String baslik = hashMap.get("Baslik");
                    String aciklama = hashMap.get("Aciklama");
                    String tarih = hashMap.get("Tarih");
                    String id = hashMap.get("Not Id");
                    notlarArrayList.add(new Notlar(aciklama, baslik, tarih, id));
                    notlarAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return notlarArrayList;
    }
    @Override
    public void MyListener(Notlar notlar) {
        Intent intent = new Intent(getApplicationContext(), NotDetayiActivity.class);
        intent.putExtra("aciklama", notlar.getNotAciklama());
        intent.putExtra("baslik", notlar.getNotBaslik());
        intent.putExtra("tarih", notlar.getNotTarih());
        intent.putExtra("id", notlar.getNotID());
        startActivity(intent);
    }

    public  void NotEkleFab(View view){
        Intent intent = new Intent(getApplicationContext(), NotEkleActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ustmenu, menu);
        MenuItem searchItem = menu.findItem(R.id.Ara);
        MenuItem logout = menu.findItem(R.id.CikisYapButonu);
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent =new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                mAuth.getInstance().signOut();
                return false;
            }
        });
        SearchView searchView=null;
        if (searchItem!=null){
            searchView=(SearchView) searchItem.getActionView();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                s=s.toLowerCase();
                ArrayList<Notlar> myList=new ArrayList<>();
                for (Notlar notlar:notlarArrayList){
                    String isim=notlar.getNotBaslik().toLowerCase();
                    if (isim.contains(s))
                        myList.add(notlar);
                }
                notlarAdapter.search(myList);
                return false;
            }
        });
        return true;
    }
}
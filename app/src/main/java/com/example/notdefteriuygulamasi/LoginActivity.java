package com.example.notdefteriuygulamasi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText sifre;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.toolbar));
        email=(EditText) findViewById(R.id.EmailText);
        sifre=(EditText) findViewById((R.id.SifreText));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        if (user!=null){
            Intent intent=new Intent(getApplicationContext(),AnaEkranActivity.class);
            startActivity(intent);
        }
    }

    public void GirisYap(final View view){
        if (email.getText().length()>0 && sifre.getText().length()>0){
            mAuth.signInWithEmailAndPassword(email.getText().toString(),sifre.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent=new Intent(getApplicationContext(),AnaEkranActivity.class);
                                startActivity(intent);
                            }
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar.make(view,"Email veya şifre yanlış!",Snackbar.LENGTH_LONG).show();
                }
            });
        }else
            Snackbar.make(view,"Email veya şifre alanları boş bırakılamaz!",Snackbar.LENGTH_LONG).show();
    }

    public void KayitOl(final View view){
        if ( email.getText().length()>0 && sifre.getText().length()>0){
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), sifre.getText().toString())
                    .addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getApplicationContext(),"Kullanıcı Başarıyla Oluşturuldu.",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),AnaEkranActivity.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Kullanıcı Oluşturma Hatası!",Toast.LENGTH_SHORT).show();
                        }
                    });
        }else
            Snackbar.make(view,"Email veya şifre alanları boş bırakılamaz!",Snackbar.LENGTH_LONG).show();
    }
}
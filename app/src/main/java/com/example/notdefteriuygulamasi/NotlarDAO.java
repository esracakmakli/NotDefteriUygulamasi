package com.example.notdefteriuygulamasi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotlarDAO {
    public ArrayList<Notlar> tumNotlar(Veritabani vt){
        ArrayList<Notlar> notlarArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM notlar",null);

        while (c.moveToNext()){
            Notlar n = new Notlar(c.getString(c.getColumnIndex("not_id"))
                    ,c.getString(c.getColumnIndex("not_baslik"))
                    ,c.getString(c.getColumnIndex("not_aciklama")),
                    c.getString(c.getColumnIndex("not_tarih")));

            notlarArrayList.add(n);
        }

        return notlarArrayList;
    }
    public void notSil(Veritabani vt,int not_id){
        SQLiteDatabase db=vt.getWritableDatabase();
        db.delete("notlar", "not_id=?",new String[] {String.valueOf(not_id)} );
        db.close();

    }

    public void notEkle(Veritabani vt,String not_baslik,String not_aciklama,String not_tarih){

        SQLiteDatabase dbx=vt.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("not_baslik",not_baslik );
        values.put("not_aciklama",not_aciklama );
        values.put("not_tarih",not_tarih );

        dbx.insertOrThrow("notlar", null, values);
        dbx.close();

    }

    public void notGuncelle(Veritabani vt,int not_id,String not_baslik,String not_aciklama,String not_tarih){

        SQLiteDatabase dbx=vt.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("not_baslik",not_baslik );
        values.put("not_aciklama",not_aciklama );
        values.put("not_tarih",not_tarih );

        dbx.update("notlar", values,"not_id=?",new String[] {String.valueOf(not_id)});
        dbx.close();

    }
}

package com.example.notdefteriuygulamasi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Veritabani extends SQLiteOpenHelper {

    public Veritabani(Context context) {
        super(context, "notlar_vt", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `notlar` (\n" +
                "\t`not_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`not_baslik`\tTEXT,\n" +
                "\t`not_aciklama`\tTEXT,\n" +
                "\t`not_tarih`\tDATE DEFAULT CURRENT_TIMESTAMP\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notlar");
        onCreate(db);
    }
}

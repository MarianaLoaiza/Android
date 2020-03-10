package com.example.mypetstoredriwer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class adminSQLiteOpenHelper extends SQLiteOpenHelper {
    public adminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
       // BaseDeDatos.execSQL("create table articulos(password int primary key, name text)");
        BaseDeDatos.execSQL("create table registro(password int primary key, name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int i, int i1) {
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS articulos");
        BaseDeDatos.execSQL("DROP TABLE IF EXISTS registro");
        this.onCreate(BaseDeDatos);
    }
}

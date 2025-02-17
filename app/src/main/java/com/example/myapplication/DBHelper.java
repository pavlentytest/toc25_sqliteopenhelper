package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users2025.db";
    public static final String TABLE_NAME = "users";
    public static final int SCHEMA = 2; // версия БД

    // Название столбцов
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_YEAR = "year";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+ "( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_YEAR + " INTEGER); "
                );
        db.execSQL("INSERT INTO "+TABLE_NAME + "("+COLUMN_NAME+","+COLUMN_YEAR+") VALUES ('Ivanov Ivan','2010')");
        db.execSQL("INSERT INTO "+TABLE_NAME + "("+COLUMN_NAME+","+COLUMN_YEAR+") VALUES ('Petrova Olga','2012')");
        db.execSQL("INSERT INTO "+TABLE_NAME + "("+COLUMN_NAME+","+COLUMN_YEAR+") VALUES ('Sidorov Alex','2015')");
    }

    public ArrayList<User> getAll() {
        ArrayList<User> userArrayList = new ArrayList<>();
        String sql = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(1),Integer.parseInt(cursor.getString(2)));
                userArrayList.add(user);
            } while  (cursor.moveToNext());
        }
        cursor.close();
        return userArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

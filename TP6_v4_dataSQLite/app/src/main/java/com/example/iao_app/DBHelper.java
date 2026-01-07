package com.example.iao_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "etudiant.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE Etudiant (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nom TEXT NOT NULL," +
                        "age INTEGER NOT NULL," +
                        "ecole TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Etudiant");
        onCreate(db);
    }

    // INSERT
    public long insertEtudiant(String nom, int age, String ecole) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", nom);
        cv.put("age", age);
        cv.put("ecole", ecole);
        return db.insert("Etudiant", null, cv);
    }

    // SELECT
    public Cursor getAllEtudiants() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Etudiant", null);
    }

    // UPDATE
    public int updateEtudiant(int id, String nom, int age, String ecole) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", nom);
        cv.put("age", age);
        cv.put("ecole", ecole);
        return db.update("Etudiant", cv, "id=?", new String[]{String.valueOf(id)});
    }

    // DELETE
    public int deleteEtudiant(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Etudiant", "id=?", new String[]{String.valueOf(id)});
    }
}

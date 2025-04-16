package com.rayeleo.smarttasks.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_tasks.db";
    private static final int DATABASE_VERSION = 2;


    private static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS tarefas (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome TEXT NOT NULL, " +
            "descricao TEXT NOT NULL, " +
            "data TEXT NOT NULL, " +
            "prioridade INTEGER DEFAULT 0);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tarefas");
        onCreate(db);
    }
}

package com.rayeleo.smarttasks.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.rayeleo.smarttasks.data.DBHelper;
import com.rayeleo.smarttasks.entities.TarefaEntity;

public class TarefaRepository {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public TarefaRepository(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long create(TarefaEntity tarefa) {
        ContentValues values = new ContentValues();
        values.put("nome", tarefa.getNome());
        values.put("descricao", tarefa.getDescricao());
        values.put("data", tarefa.getData());
        return database.insert("tarefas", null, values);
    }

    public Cursor findAll() {
        return database.query("tarefas", null, null, null, null, null, null);
    }
}

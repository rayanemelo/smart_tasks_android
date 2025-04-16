package com.rayeleo.smarttasks.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.rayeleo.smarttasks.data.DBHelper;
import com.rayeleo.smarttasks.entities.TarefaEntity;
import com.rayeleo.smarttasks.enums.PrioridadeTarefa;

import java.util.ArrayList;
import java.util.List;

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
        values.put("prioridade", tarefa.getPrioridade().getValor());
        return database.insert("tarefas", null, values);
    }

    public List<TarefaEntity> findAll() {
        Cursor cursor = database.query("tarefas",
                new String[]{
                "id",
                "nome",
                "descricao",
                "data",
                "prioridade"
        }, null, null, null, null, null);

        List<TarefaEntity> tarefas = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                TarefaEntity tarefa = new TarefaEntity();
                tarefa.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                tarefa.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
                tarefa.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
                tarefa.setData(cursor.getString(cursor.getColumnIndexOrThrow("data")));
                tarefa.setPrioridade(PrioridadeTarefa.fromValor(cursor.getInt(cursor.getColumnIndexOrThrow("prioridade"))));
                tarefas.add(tarefa);
            } while (cursor.moveToNext());
        }

        return tarefas;
    }
}

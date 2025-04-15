package com.rayeleo.smarttasks;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rayeleo.smarttasks.adapters.TarefaAdapter;
import com.rayeleo.smarttasks.repositories.TarefaRepository;

public class ListagemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private TarefaRepository tarefaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listagem);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tarefaRepository = new TarefaRepository(this);
        tarefaRepository.open();

        Cursor cursor = tarefaRepository.findAll();

        tarefaAdapter = new TarefaAdapter(this, cursor);
        recyclerView.setAdapter(tarefaAdapter);

        tarefaRepository.close();
    }
}
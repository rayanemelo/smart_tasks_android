package com.rayeleo.smarttasks;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rayeleo.smarttasks.adapters.TarefaAdapter;
import com.rayeleo.smarttasks.entities.TarefaEntity;
import com.rayeleo.smarttasks.repositories.TarefaRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListagemInteligenteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private TarefaRepository tarefaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listagem_inteligente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewTarefasInteligente);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tarefaRepository = new TarefaRepository(this);
        tarefaRepository.open();

        List<TarefaEntity> tarefas = aplicarAlgoritmoInteligente(tarefaRepository.findAll());

        tarefaAdapter = new TarefaAdapter(this, tarefas, null);
        recyclerView.setAdapter(tarefaAdapter);

        tarefaRepository.close();
    }

    private List<TarefaEntity> aplicarAlgoritmoInteligente(List<TarefaEntity> tarefas) {
        tarefas.sort((t1, t2) -> {
            int prioridadeCompare = Integer.compare(t2.getPrioridade().getValor(), t1.getPrioridade().getValor());
            if (prioridadeCompare != 0) {
                return prioridadeCompare;
            }
            return t1.getData().compareTo(t2.getData());
        });

        return tarefas;
    }
}
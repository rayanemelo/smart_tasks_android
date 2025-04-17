package com.rayeleo.smarttasks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rayeleo.smarttasks.adapters.TarefaAdapter;
import com.rayeleo.smarttasks.entities.TarefaEntity;
import com.rayeleo.smarttasks.repositories.TarefaRepository;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TarefaRepository tarefaRepository;
    private TarefaAdapter tarefaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void abrirTelaCadastro(View v) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void abrirTelaListagem(View v) {
        Intent intent = new Intent(this, ListagemActivity.class);
        startActivity(intent);
    }

    public void abrirCompartilhamento(View v) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_lista_opcoes, null);
        RecyclerView recyclerView = dialogView.findViewById(R.id.recyclerViewOpcoes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        tarefaRepository = new TarefaRepository(this);
        tarefaRepository.open();

        List<TarefaEntity> tarefas = tarefaRepository.findAll();

        View.OnClickListener clickListenerModal = v1 -> {
            TarefaEntity tarefa = (TarefaEntity) v1.getTag();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Tarefa: " + tarefa.getNome());
            intent.putExtra(Intent.EXTRA_TEXT, tarefa.toString());
            startActivity(Intent.createChooser(intent, "Compartilhar tarefa via"));
        };

        tarefaAdapter = new TarefaAdapter(this, tarefas, clickListenerModal);
        recyclerView.setAdapter(tarefaAdapter);

        tarefaRepository.close();
        dialog.show();
    }

    public void abrirTelaAnaliseInteligente(View v) {
        Intent intent = new Intent(this, ListagemInteligenteActivity.class);
        startActivity(intent);
    }

    public void abrirTelaSobre(View v) {
        Intent intent = new Intent(this, SobreActivity.class);
        startActivity(intent);
    }

    public void abrirSiteDoCurso(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ifrs.edu.br/rolante/tecnologia-em-analise-e-desenvolvimento-de-sistemas/"));
        startActivity(intent);
    }
}
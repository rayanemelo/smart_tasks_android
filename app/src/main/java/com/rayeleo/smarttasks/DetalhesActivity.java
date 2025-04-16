package com.rayeleo.smarttasks;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rayeleo.smarttasks.entities.TarefaEntity;

public class DetalhesActivity extends AppCompatActivity {

    private TextView nomeText, descricaoText, dataText,prioridadeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nomeText = findViewById(R.id.textNomeDetalhe);
        descricaoText = findViewById(R.id.textDescricaoDetalhe);
        dataText = findViewById(R.id.textDataDetalhe);
        prioridadeText = findViewById(R.id.textPrioridadeDetalhe);

        TarefaEntity tarefa = getIntent().getParcelableExtra("tarefa");

        if (tarefa != null) {
            nomeText.setText(tarefa.getNome());
            descricaoText.setText(tarefa.getDescricao());
            dataText.setText(tarefa.getData());
           prioridadeText.setText(tarefa.getPrioridade().name());
        }
    }
}
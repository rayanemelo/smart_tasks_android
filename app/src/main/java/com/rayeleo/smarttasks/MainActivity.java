package com.rayeleo.smarttasks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
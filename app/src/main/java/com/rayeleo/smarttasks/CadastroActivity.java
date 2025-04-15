package com.rayeleo.smarttasks;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rayeleo.smarttasks.entities.TarefaEntity;
import com.rayeleo.smarttasks.repositories.TarefaRepository;

import java.util.Calendar;

public class CadastroActivity extends AppCompatActivity {

    public EditText editTextTitulo, editTextDescricao, editTextData;
    public Button buttonSalvar;

    private TarefaRepository tarefaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tarefaRepository = new TarefaRepository(this);
        tarefaRepository.open();

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        editTextData = findViewById(R.id.editTextData);
        buttonSalvar = findViewById(R.id.buttonSave);

        editTextData.setFocusable(false);
        editTextData.setClickable(true);

        editTextData.setOnClickListener(view -> {
            Calendar calendario = Calendar.getInstance();
            int ano = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    CadastroActivity.this,
                    (DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) -> {
                        String dataEscolhida = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        editTextData.setText(dataEscolhida);
                    },
                    ano, mes, dia
            );
            datePickerDialog.show();
        });
    }

    public void salvarTarefa(View v){
        String titulo = editTextTitulo.getText().toString().trim();
        String descricao = editTextDescricao.getText().toString().trim();
        String data = editTextData.getText().toString().trim();

        if (titulo.isEmpty()) {
            editTextTitulo.setError("O título é obrigatório");
            return;
        }

        if (descricao.isEmpty()) {
            editTextDescricao.setError("A descrição é obrigatória");
            return;
        }

        if (data.isEmpty()) {
            editTextData.setError("A data é obrigatória");
            return;
        }

        TarefaEntity novaTarefa = new TarefaEntity(titulo, descricao, data);
        tarefaRepository.create(novaTarefa);
    }
}
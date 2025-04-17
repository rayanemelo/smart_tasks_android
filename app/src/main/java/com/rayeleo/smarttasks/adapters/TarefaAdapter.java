package com.rayeleo.smarttasks.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rayeleo.smarttasks.DetalhesActivity;
import com.rayeleo.smarttasks.R;
import com.rayeleo.smarttasks.entities.TarefaEntity;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private Context context;
    private List<TarefaEntity> tarefas;

    private View.OnClickListener externalClickListener;

    public TarefaAdapter(Context context, List<TarefaEntity> tarefas, View.OnClickListener clickListener) {
        this.context = context;
        this.tarefas = tarefas;
        this.externalClickListener = clickListener;
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false);
        return new TarefaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TarefaViewHolder holder, int position) {
        TarefaEntity tarefa = tarefas.get(position);
        holder.nomeTextView.setText(tarefa.getNome());
        holder.descricaoTextView.setText(tarefa.getDescricao());
        holder.dataTextView.setText(tarefa.getData());
        holder.prioridadeTextView.setText(tarefa.getPrioridade().name());

        if (externalClickListener != null) {
            // Ação de clique personalizada
            holder.itemView.setTag(tarefa);
            holder.itemView.setOnClickListener(externalClickListener);
        } else {
            // Comportamento padrão
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetalhesActivity.class);
                intent.putExtra("tarefa", tarefa);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return tarefas != null ? tarefas.size() : 0;
    }

    public class TarefaViewHolder extends RecyclerView.ViewHolder {

        TextView nomeTextView;
        TextView descricaoTextView;
        TextView dataTextView;
        TextView prioridadeTextView;

        public TarefaViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.textViewNome);
            descricaoTextView = itemView.findViewById(R.id.textViewDescricao);
            dataTextView = itemView.findViewById(R.id.textViewData);
            prioridadeTextView = itemView.findViewById(R.id.textViewPrioridade);
        }
    }
}

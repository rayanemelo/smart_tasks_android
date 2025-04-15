package com.rayeleo.smarttasks.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rayeleo.smarttasks.R;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private Context context;
    private Cursor cursor;

    public TarefaAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false);
        return new TarefaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TarefaViewHolder holder, int position) {
        if (cursor != null && cursor.moveToPosition(position)) {
            int nomeIndex = cursor.getColumnIndex("nome");
            int descricaoIndex = cursor.getColumnIndex("descricao");
            int dataIndex = cursor.getColumnIndex("data");

            if (nomeIndex >= 0 && descricaoIndex >= 0 && dataIndex >= 0) {
                String nome = cursor.getString(nomeIndex);
                String descricao = cursor.getString(descricaoIndex);
                String data = cursor.getString(dataIndex);

                holder.nomeTextView.setText(nome);
                holder.descricaoTextView.setText(descricao);
                holder.dataTextView.setText(data);
            }
        }
    }

    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    public class TarefaViewHolder extends RecyclerView.ViewHolder {

        TextView nomeTextView;
        TextView descricaoTextView;
        TextView dataTextView;

        public TarefaViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.textViewNome);
            descricaoTextView = itemView.findViewById(R.id.textViewDescricao);
            dataTextView = itemView.findViewById(R.id.textViewData);
        }
    }
}

package com.rayeleo.smarttasks.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.rayeleo.smarttasks.enums.PrioridadeTarefa;

public class TarefaEntity implements Parcelable {

    private int id;
    private String nome;
    private String descricao;
    private String data;

    private PrioridadeTarefa prioridade;

    public TarefaEntity(String nome, String descricao, String data, PrioridadeTarefa prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.prioridade = prioridade;
    }

    public TarefaEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public PrioridadeTarefa getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeTarefa prioridade) {
        this.prioridade = prioridade;
    }

    protected TarefaEntity(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        descricao = in.readString();
        data = in.readString();
        prioridade = PrioridadeTarefa.fromValor(in.readInt());
    }

    public static final Creator<TarefaEntity> CREATOR = new Creator<TarefaEntity>() {
        @Override
        public TarefaEntity createFromParcel(Parcel in) {
            return new TarefaEntity(in);
        }

        @Override
        public TarefaEntity[] newArray(int size) {
            return new TarefaEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(nome);
        parcel.writeString(descricao);
        parcel.writeString(data);
        parcel.writeInt(prioridade.getValor());
    }
}

package com.rayeleo.smarttasks.enums;

public enum PrioridadeTarefa {
    BAIXA(1),
    MEDIA(2),
    ALTA(3);

    private final int valor;

    PrioridadeTarefa(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static PrioridadeTarefa fromValor(int valor) {
        for (PrioridadeTarefa prioridade : values()) {
            if (prioridade.getValor() == valor) {
                return prioridade;
            }
        }
        throw new IllegalArgumentException("Valor inválido para PrioridadeTarefa: " + valor);
    }
}

package com.mycompany.sistemareciclagem;

public class Recompensa {
    private String nome;
    private String descricao;
    private int custoPontos;

    public Recompensa(String nome, String descricao, int custoPontos) {
        this.nome = nome;
        this.descricao = descricao;
        this.custoPontos = custoPontos;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCustoPontos() {
        return custoPontos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCustoPontos(int custoPontos) {
        this.custoPontos = custoPontos;
    }
    
    
}

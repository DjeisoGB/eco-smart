package com.mycompany.sistemareciclagem;

public class Recompensa {
    private String nome;
    private String descricao;
    private int custoPontos;
    private String localRetirada;

    public Recompensa(String nome, String descricao, int custoPontos, String localRetirada) {
        this.nome = nome;
        this.descricao = descricao;
        this.custoPontos = custoPontos;
        this.localRetirada = localRetirada;
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
    
    public String getLocalRetirada() {
        return localRetirada;
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

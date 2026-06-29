package com.mycompany.sistemareciclagem;

import java.util.ArrayList;

public class Usuario extends Pessoa{
    private int pontos;

    private ArrayList<RegistroDescarte> historico;

    public Usuario(String nome,String email, String senha) {
        super(nome, email, senha);
        this.pontos = 0;
        this.historico = new ArrayList<>();
    }

    public int getPontos() {
        return pontos;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void removerPontos(int pontos) {
        this.pontos -= pontos;
    }
    
    public ArrayList<RegistroDescarte> getHistorico() {
        return historico;
    }
}

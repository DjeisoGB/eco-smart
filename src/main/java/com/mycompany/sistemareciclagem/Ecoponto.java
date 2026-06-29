package com.mycompany.sistemareciclagem;

import java.util.ArrayList;

public class Ecoponto {
    private String nome;
    private String endereco;
    private ArrayList<Residuo> residuosAceitos;

    public Ecoponto(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        residuosAceitos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public ArrayList<Residuo> getResiduosAceitos() {
        return residuosAceitos;
    }
    
    public void adicionarResiduo(Residuo residuo) {
        residuosAceitos.add(residuo);
    }
}

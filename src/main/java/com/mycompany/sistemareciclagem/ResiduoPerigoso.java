package com.mycompany.sistemareciclagem;

public class ResiduoPerigoso extends Residuo{
    public ResiduoPerigoso(String nome, String descricao, String localDescarte) {
        super(nome, TipoResiduo.PERIGOSO, CorLixeira.LARANJA, descricao, localDescarte);
    }

    @Override
    public int calcularPontos(int quantidade) {
        return quantidade * 20;
    }
}

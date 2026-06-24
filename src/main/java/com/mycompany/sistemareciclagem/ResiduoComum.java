package com.mycompany.sistemareciclagem;

public class ResiduoComum extends Residuo{
    public ResiduoComum(String nome, TipoResiduo tipo,CorLixeira cor, String descricao, String localDescarte) {
        super(nome, tipo, cor, descricao, localDescarte);
    }

    @Override
    public int calcularPontos(int quantidade) {
        return quantidade * 10;
    }
}

package com.mycompany.sistemareciclagem;

public class ResiduoEletronico extends Residuo{
    public ResiduoEletronico(String nome, String descricao, String localDescarte) {
        super(nome, TipoResiduo.ELETRONICO, CorLixeira.LARANJA, descricao, localDescarte);
    }

    @Override
    public int calcularPontos(int quantidade) {
        return quantidade * 50;
    }
}

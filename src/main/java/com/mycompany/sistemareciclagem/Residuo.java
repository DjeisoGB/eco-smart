package com.mycompany.sistemareciclagem;

public abstract class Residuo implements Pontuavel{
    private String nome;
    private TipoResiduo tipo;
    private CorLixeira corLixeira;
    private String descricao;
    private String localDescarte;

    public Residuo(String nome, TipoResiduo tipo, CorLixeira corLixeira, String descricao, String localDescarte) {
        this.nome = nome;
        this.tipo = tipo;
        this.corLixeira = corLixeira;
        this.descricao = descricao;
        this.localDescarte = localDescarte;
    }

    public String getNome() {
        return nome;
    }

    public TipoResiduo getTipo() {
        return tipo;
    }

    public CorLixeira getCorLixeira() {
        return corLixeira;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalDescarte() {
        return localDescarte;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLocalDescarte(String localDescarte) {
        this.localDescarte = localDescarte;
    }
    
    
    
}

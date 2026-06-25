package com.mycompany.sistemareciclagem;

import java.time.LocalDate;

public class RegistroDescarte {
    private Usuario usuario;
    private Residuo residuo;
    private int quantidade;
    private int pontosGerados;
    private LocalDate data;

    public RegistroDescarte(Usuario usuario, Residuo residuo, int quantidade, int pontosGerados) {
        this.usuario = usuario;
        this.residuo = residuo;
        this.quantidade = quantidade;
        this.pontosGerados = pontosGerados;

        data = LocalDate.now();
    }
    
    public RegistroDescarte(Usuario usuario, Residuo residuo, int quantidade, int pontosGerados, LocalDate data) {

        this.usuario = usuario;
        this.residuo = residuo;
        this.quantidade = quantidade;
        this.pontosGerados = pontosGerados;
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Residuo getResiduo() {
        return residuo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getPontosGerados() {
        return pontosGerados;
    }
    
    public LocalDate getData() {
        return data;
    }    
}

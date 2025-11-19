package com.webacademy.padaria.imagem;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Imagem {
    @Column(name = "nome_imagem", columnDefinition = "TEXT")
    private String nomeArquivo;

    private boolean publica;

    public Imagem(String nomeArquivo, boolean publica) {
        this.nomeArquivo = nomeArquivo;
        this.publica = publica;
    }

    public Imagem() {
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    
}

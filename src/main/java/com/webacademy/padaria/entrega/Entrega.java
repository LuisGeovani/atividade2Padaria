package com.webacademy.padaria.entrega;

import java.time.Instant;

import com.webacademy.padaria.endereco.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="entregas")
public class Entrega {
    @Id
    private Long codigoConfirmacao;

    @Column(nullable=false)
    private Instant previsaoEntrega;

    @Column(nullable=false)
    private Instant momentoEntrega;

    @Column(nullable=false)
    private boolean retirada;

    @ManyToOne
    @JoinColumn(name="endereco_id")
    private Endereco endereco;

    public Long getCodigoConfirmacao() {
        return codigoConfirmacao;
    }

    public void setCodigoConfirmacao(Long codigoConfirmacao) {
        this.codigoConfirmacao = codigoConfirmacao;
    }

    public Instant getPrevisaoEntrega() {
        return previsaoEntrega;
    }

    public void setPrevisaoEntrega(Instant previsaoEntrega) {
        this.previsaoEntrega = previsaoEntrega;
    }

    public Instant getMomentoEntrega() {
        return momentoEntrega;
    }

    public void setMomentoEntrega(Instant momentoEntrega) {
        this.momentoEntrega = momentoEntrega;
    }

    public boolean isRetirada() {
        return retirada;
    }

    public void setRetirada(boolean retirada) {
        this.retirada = retirada;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    
}

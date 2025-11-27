package com.webacademy.padaria.produto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.webacademy.padaria.categoria.Categoria;
import com.webacademy.padaria.imagem.Imagem;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 1024)
    private String descricao;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean ativo;

    @Column(nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ElementCollection
    @CollectionTable(
        name = "produto_imagens",
        joinColumns = @JoinColumn(name = "produto_id")
    )
    private Set<Imagem> imagens = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(Set<Imagem> imagens) {
        this.imagens = imagens;
    }


}

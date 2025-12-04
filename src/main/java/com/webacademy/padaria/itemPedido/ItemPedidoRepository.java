package com.webacademy.padaria.itemPedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
    
    @Query("""
        SELECT i FROM ItemPedido i
        WHERE i.produto.nome LIKE %:termoBusca%
        """)
    List<ItemPedido> busca(String termoBusca);
}

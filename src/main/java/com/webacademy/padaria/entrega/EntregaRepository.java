package com.webacademy.padaria.entrega;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EntregaRepository extends JpaRepository<Entrega, Long>{
    @Query("""
    SELECT ent FROM Entrega ent
    JOIN ent.endereco ender
    WHERE ender.rua LIKE %:termoBusca% 
    OR ender.cidade LIKE %:termoBusca%
    """)
    List<Entrega> busca(String termoBusca);
}

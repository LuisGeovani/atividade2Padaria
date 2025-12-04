package com.webacademy.padaria.endereco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
    @Query("""
            SELECT p FROM Endereco p
            WHERE p.rua LIKE %:termoBusca%
            """)
    List<Endereco> busca(String termoBusca);
}

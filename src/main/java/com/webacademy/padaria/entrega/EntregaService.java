package com.webacademy.padaria.entrega;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webacademy.padaria.infra.EntidadeNotFound;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.IDeleteService;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.IGetService;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.ISaveService;

@Service
public class EntregaService implements IGetService<Entrega>, ISaveService<Entrega>, IDeleteService{
    private final EntregaRepository repository;

    public EntregaService(EntregaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Long id) {
        var entrega = get(id);
        repository.delete(entrega);
    }

    @Override
    public Entrega save(Entrega objeto) {
        return repository.save(objeto);
    }

    @Override
    public Entrega get(Long id) {
        var entrega = repository.findById(id).orElseThrow(() -> new EntidadeNotFound("Entrega nao encontrada"));
        return entrega;
    }

    @Override
    public List<Entrega> get(String termoBusca) {
        if (termoBusca != null && !termoBusca.isBlank()){
            var entregas = repository.busca(termoBusca);
            return entregas;
        }
        return repository.findAll();
    }
    
    
}

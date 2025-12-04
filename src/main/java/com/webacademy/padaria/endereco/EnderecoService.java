package com.webacademy.padaria.endereco;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webacademy.padaria.infra.EntidadeNotFound;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.IDeleteService;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.IGetService;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.ISaveService;

@Service
public class EnderecoService implements IGetService<Endereco>, ISaveService<Endereco>, IDeleteService{

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Long id) {
        var endereco = get(id);
        repository.delete(endereco);
    }

    @Override
    public Endereco save(Endereco objeto) {
        return repository.save(objeto);
    }

    @Override
    public Endereco get(Long id) {
        var endereco = repository.findById(id).orElseThrow(() -> new EntidadeNotFound("Endereço não encontrado"));
        return endereco;
    }

    @Override
    public List<Endereco> get(String termoBusca) {
        if (termoBusca != null && !termoBusca.isBlank()){
            var enderecos = repository.busca(termoBusca);
            return enderecos;
        }
        return repository.findAll();
    }
    
}

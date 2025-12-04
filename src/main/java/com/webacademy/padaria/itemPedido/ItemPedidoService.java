package com.webacademy.padaria.itemPedido;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webacademy.padaria.infra.EntidadeNotFound;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.IDeleteService;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.IGetService;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.ISaveService;

@Service
public class ItemPedidoService implements IGetService<ItemPedido>, ISaveService<ItemPedido>, IDeleteService{
    
    private final ItemPedidoRepository repository;

    public ItemPedidoService(ItemPedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Long id) {
        var itemPedido = get(id);
        repository.delete(itemPedido);
    }

    @Override
    public ItemPedido save(ItemPedido objeto) {
        return repository.save(objeto);
    }

    @Override
    public ItemPedido get(Long id) {
        var itemPedido = repository.findById(id).orElseThrow(() -> new EntidadeNotFound("Quantidade de itens não encontrada"));
        return itemPedido;
    }

    @Override
    public List<ItemPedido> get(String termoBusca) {
        if (termoBusca != null && !termoBusca.isBlank()){
            var itensPedidos = repository.busca(termoBusca);
            return itensPedidos;
        }
        return repository.findAll();
    }


}

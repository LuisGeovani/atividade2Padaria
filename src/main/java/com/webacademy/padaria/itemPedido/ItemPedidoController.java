package com.webacademy.padaria.itemPedido;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webacademy.padaria.endereco.EnderecoController;
import com.webacademy.padaria.interfaces.crudControllerInterfaces.IDeleteController;
import com.webacademy.padaria.interfaces.crudControllerInterfaces.IGetController;
import com.webacademy.padaria.interfaces.crudControllerInterfaces.ISaveController;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController implements IGetController<ItemPedido>, ISaveController<ItemPedido>, IDeleteController{

    private final EnderecoController enderecoController;
    
    private final ItemPedidoService service;

    public ItemPedidoController(ItemPedidoService service, EnderecoController enderecoController) {
        this.service = service;
        this.enderecoController = enderecoController;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<ItemPedido> insert(@RequestBody ItemPedido objeto) {
        var itemPedido = service.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemPedido);
    }

    @PutMapping
    public ResponseEntity<ItemPedido> update(@RequestBody ItemPedido objeto) {
        var itemPedido = service.save(objeto);
        return ResponseEntity.ok(itemPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> get(@PathVariable Long id) {
        var itemPedido = service.get(id);
        if (itemPedido == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(itemPedido);
    }

    @GetMapping
    public ResponseEntity<List<ItemPedido>> get(@RequestParam(required=false) String termoBusca) {
        var registros = service.get(termoBusca);
        return ResponseEntity.ok(registros);
    }

    
}

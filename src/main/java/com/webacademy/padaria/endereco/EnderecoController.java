package com.webacademy.padaria.endereco;

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

import com.webacademy.padaria.interfaces.crudControllerInterfaces.IDeleteController;
import com.webacademy.padaria.interfaces.crudControllerInterfaces.IGetController;
import com.webacademy.padaria.interfaces.crudControllerInterfaces.ISaveController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController implements IGetController<Endereco>, ISaveController<Endereco>, IDeleteController{

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<Endereco> insert(@RequestBody Endereco objeto) {
        var endereco = service.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    @PutMapping
    public ResponseEntity<Endereco> update(@RequestBody Endereco objeto) {
        var endereco = service.save(objeto);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> get(@PathVariable Long id) {
        var endereco = service.get(id);
        if (endereco == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(endereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> get(@RequestParam(required=false) String termoBusca) {
        var registros = service.get(termoBusca);
        return ResponseEntity.ok(registros);
    }
    
}

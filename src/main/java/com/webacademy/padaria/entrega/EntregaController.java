package com.webacademy.padaria.entrega;

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
@RequestMapping("/entregas")
public class EntregaController implements IGetController<Entrega>, ISaveController<Entrega>, IDeleteController{
    private final EntregaService service;

    public EntregaController(EntregaService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<Entrega> insert(@RequestBody Entrega objeto) {
        var entrega = service.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrega);
    }

    @PutMapping
    public ResponseEntity<Entrega> update(@RequestBody Entrega objeto) {
        var entrega = service.save(objeto);
        if (entrega == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(entrega);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> get(@PathVariable Long id) {
        var entrega = service.get(id);
        if (entrega == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(entrega);
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> get(@RequestParam(required=false) String termoBusca) {
        var registros = service.get(termoBusca);
        return ResponseEntity.ok(registros);
    }
    
}

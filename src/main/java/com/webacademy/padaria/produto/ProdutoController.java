package com.webacademy.padaria.produto;

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
import org.springframework.web.multipart.MultipartFile;

import com.webacademy.padaria.interfaces.crudControllerInterfaces.IDeleteController;
import com.webacademy.padaria.interfaces.crudControllerInterfaces.IGetController;
import com.webacademy.padaria.interfaces.crudControllerInterfaces.ISaveController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController implements IGetController<Produto>, ISaveController<Produto>, IDeleteController {
    
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
       service.delete(id);
       return ResponseEntity.ok(null); 
    }

    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto objeto) {
        var produto = service.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping
    public ResponseEntity<Produto> update(@RequestBody Produto objeto) {
        var produto = service.save(objeto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> get(@PathVariable Long id) {
        var produto = service.get(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> get(@RequestParam(required = false) String termoBusca) {
        var produtos = service.get(termoBusca);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping("/{idProduto}/imagem")
    public ResponseEntity<Produto> addImagens(@PathVariable Long idProduto,
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("publica") boolean publica) {
        var produto = service.addImagens(idProduto, files, publica);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{idProduto}/imagem")
    public ResponseEntity<Void> removerImagem(@PathVariable Long idProduto,
            @RequestParam("nomeArquivo") String nomeArquivo) {
        service.excluirImagem(idProduto, nomeArquivo);
        return ResponseEntity.ok().body(null);
    }

}

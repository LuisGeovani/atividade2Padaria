package com.webacademy.padaria.produto;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webacademy.padaria.categoria.CategoriaRepository;
import com.webacademy.padaria.imagem.Imagem;
import com.webacademy.padaria.infra.EntidadeNotFound;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.IDeleteService;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.IGetService;
import com.webacademy.padaria.interfaces.crudServiceInterfaces.ISaveService;
import com.webacademy.padaria.storage.FileSystemStorageService;

@Service
public class ProdutoService implements IGetService<Produto>, ISaveService<Produto>, IDeleteService {

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final FileSystemStorageService fileService;

    public ProdutoService(ProdutoRepository repository, FileSystemStorageService fileService, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.fileService = fileService;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void delete(Long id) {
        var produto = get(id);
        var imagens = produto.getImagens();

        imagens.stream().forEach(
            img -> fileService.delete(img.getNomeArquivo(), img.isPublica())
        );
        
        repository.delete(produto);
    }

    @Override
    public Produto save(Produto objeto) {
        if (objeto.getCategoria() != null && objeto.getCategoria().getId() != null) {
            var id = objeto.getCategoria().getId();
            var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNotFound("Categoria não encontrada!"));
            objeto.setCategoria(categoria);
        }
        var produto = repository.save(objeto);
        return produto;
    }

    @Override
    public Produto get(Long id) {
        var produto = repository.findById(id)
            .orElseThrow(() -> new EntidadeNotFound("Produto não encontrado!"));
        return produto;
    }

    @Override
    public List<Produto> get(String termoBusca) {
        if (termoBusca != null && !termoBusca.isBlank()) {
            var produtos = repository.busca(termoBusca);
            return produtos;
        }
        return repository.findAll();
    }
    
    public Produto addImagens(Long produtoId, List<MultipartFile> files, boolean publica) {
        var produto = get(produtoId);
        Set<Imagem> imagens = produto.getImagens();

        for(MultipartFile file : files) {
            var nomeArquivo = fileService.store(file, publica);
            var imagem = new Imagem(nomeArquivo, publica);
            imagens.add(imagem);
        }

        return repository.save(produto);
    }

    public void excluirImagem(Long produtoId, String nomeArquivo) {
        var produto = get(produtoId);

        var imagem = produto.getImagens().stream()
            .filter(img -> img.getNomeArquivo() != null && !img.getNomeArquivo().isBlank() && img.getNomeArquivo().trim().equals(nomeArquivo.trim()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Imagem não encontrada no produto: " + nomeArquivo));
        
        produto.getImagens().remove(imagem);

        repository.save(produto);

        fileService.delete(imagem.getNomeArquivo(), imagem.isPublica());
    }
}

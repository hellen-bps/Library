package dev.impacta.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.biblioteca.entities.Livro;
import com.devsuperior.biblioteca.repositories.LivroRepository;

@RestController
@RequestMapping(value = "/livros")
//PERMITINDO LIVESERVER ACESAR ESSA ROTA
@CrossOrigin(origins = "*")
public class LivroController {
	
	@Autowired
	private LivroRepository repository;
	
	@GetMapping
	public List<Livro> findAll(){
		List<Livro> result = repository.findAll();
		return result;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id) {
	    Optional<Livro> livro = repository.findById(id);
	    if (livro.isPresent()) {
	        return ResponseEntity.ok(livro.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro livro) {
        // Verifica se o livro existe
        Optional<Livro> existingLivro = repository.findById(id);
        if (existingLivro.isPresent()) {
            // Atualiza o objeto existente com os novos dados
            Livro updatedLivro = existingLivro.get();
            updatedLivro.setNome(livro.getNome());
            updatedLivro.setAutor(livro.getAutor());
            updatedLivro.setAnoPubli(livro.getAnoPubli());
            updatedLivro.setQuantidade(livro.getQuantidade());
            updatedLivro.setCategoria(livro.getCategoria());
            
            
            // Salva as alterações no banco de dados
            repository.save(updatedLivro);
            return ResponseEntity.ok(updatedLivro);
        } else {
            // Retorna 404 Not Found se o livro não existir
            return ResponseEntity.notFound().build();
        }
    }
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    Optional<Livro> livro = repository.findById(id);
	    if (livro.isPresent()) {
	        repository.delete(livro.get());
	        return ResponseEntity.noContent().build(); // Retorna 204 No Content após a exclusão
	    } else {
	        return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o livro não existir
	    }
	}
	
    @PostMapping
    public Livro insert(@RequestBody Livro livro) {
        Livro savedLivro = repository.save(livro);
        return savedLivro;
    }

}
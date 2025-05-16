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

import com.devsuperior.biblioteca.entities.Usuario;
import com.devsuperior.biblioteca.repositories.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
//PERMITINDO LIVESERVER ACESAR ESSA ROTA
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public List<Usuario> findAll(){
		List<Usuario> result = repository.findAll();
		return result;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
	    Optional<Usuario> usuario = repository.findById(id);
	    if (usuario.isPresent()) {
	        return ResponseEntity.ok(usuario.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        // Verifica se o livro existe
        Optional<Usuario> existingUsuario = repository.findById(id);
        if (existingUsuario.isPresent()) {
            // Atualiza o objeto existente com os novos dados
            Usuario updatedUsuario = existingUsuario.get();
            updatedUsuario.setNome(usuario.getNome());
            updatedUsuario.setEndereco(usuario.getEndereco());
            updatedUsuario.setTelefone(usuario.getTelefone());
            updatedUsuario.setSenha(usuario.getSenha());
            updatedUsuario.setAdm(usuario.isAdm());
            
            
            // Salva as alterações no banco de dados
            repository.save(updatedUsuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            // Retorna 404 Not Found se o livro não existir
            return ResponseEntity.notFound().build();
        }
    }
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    Optional<Usuario> usuario = repository.findById(id);
	    if (usuario.isPresent()) {
	        repository.delete(usuario.get());
	        return ResponseEntity.noContent().build(); // Retorna 204 No Content após a exclusão
	    } else {
	        return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o livro não existir
	    }
	}
	
    @PostMapping
    public Usuario insert(@RequestBody Usuario usuario) {
        Usuario savedUsuario = repository.save(usuario);
        return savedUsuario;
    }


}

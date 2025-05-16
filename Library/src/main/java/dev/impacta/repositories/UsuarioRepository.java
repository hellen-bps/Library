package dev.impacta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.biblioteca.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByNome(String nome);
}
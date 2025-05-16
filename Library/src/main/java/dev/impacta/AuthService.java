package dev.impacta.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.devsuperior.biblioteca.entities.Usuario;
import com.devsuperior.biblioteca.repositories.UsuarioRepository;

@Service
public class AuthService {
       @Autowired
    private UsuarioRepository userRepository;

    public Usuario login(String nome, String senha) {
        Usuario usuario = userRepository.findByNome(nome);

        if (usuario == null || !senha.equals(usuario.getSenha().trim())) {
            throw new IllegalArgumentException("Nome ou senha incorretos");
        }

        return usuario;
    }
}

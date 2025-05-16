package dev.impacta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.biblioteca.entities.Usuario;
import com.devsuperior.biblioteca.services.AuthService;

@RestController()
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
     @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String nome, @RequestParam String senha) {
        try {
            Usuario usuario = authService.login(nome, senha);
            return ResponseEntity.ok(usuario); // Retorna o usuário autenticado
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body("Credenciais invalidas"); // Credenciais inválidas
        }
    }
}

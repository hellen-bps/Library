package dev.impacta.controller;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping

//Page_inicial

public class Controller {
	
	@GetMapping("/inicialpage")
	public String InicialPage() {
	return "Hello, aluno faculdade impacta, alugue sus livros";
	}
}

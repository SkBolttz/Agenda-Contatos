package br.com.Agenda.Contatos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Agenda.Contatos.DTO.LoginDTO;
import br.com.Agenda.Contatos.Entity.Usuario;
import br.com.Agenda.Contatos.Service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final LoginService loginService;  

    @Autowired
    public AuthenticationController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(summary = "Requisição para Login do usuário", description = "Requisição para Login do usuário")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO usuario) {

        String token = loginService.login(usuario);

        return ResponseEntity.status(200).body(token);
    }

    @Operation(summary = "Requisição para Cadastro do usuário", description = "Requisição para Cadastro do usuário")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid Usuario usuario) {

        String token = loginService.register(usuario);

        return ResponseEntity.status(200).body(token);
    }
}

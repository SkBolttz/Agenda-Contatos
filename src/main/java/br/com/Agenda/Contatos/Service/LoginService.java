package br.com.Agenda.Contatos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.Agenda.Contatos.DTO.LoginDTO;
import br.com.Agenda.Contatos.Entity.Usuario;
import br.com.Agenda.Contatos.Repository.UsuarioRepository;
import br.com.Agenda.Contatos.Security.TokenService;
import jakarta.validation.Valid;

@Service
public class LoginService {

    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(Usuario usuario) {

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuarioRepository.save(usuario);

        return"""
                Seja bem vindo(a) """ + usuario.getNome() + """
                """;
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDTO user) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
            user.nome(), 
            user.password()
        );
    
        var authentication = manager.authenticate(authenticationToken);
    
        var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
    
        return token;
    }
}

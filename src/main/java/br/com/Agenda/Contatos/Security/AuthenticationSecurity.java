package br.com.Agenda.Contatos.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.Agenda.Contatos.Entity.Usuario;
import br.com.Agenda.Contatos.Repository.UsuarioRepository;

@Service
public class AuthenticationSecurity implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByNome(username); 

        if (usuario != null) {
            return usuario;
        }

        return null;
    }
}

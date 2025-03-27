package br.com.Agenda.Contatos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Agenda.Contatos.Entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByNome(String username);

    Usuario findByEmailAndPassword(String email, String password);
    
}

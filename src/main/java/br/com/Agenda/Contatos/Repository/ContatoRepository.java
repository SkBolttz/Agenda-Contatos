package br.com.Agenda.Contatos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Agenda.Contatos.Entity.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

    Contato findByNome(String username);

    Contato findByTelefone(String telefone);

    Contato findByEmail(String email);
    
}

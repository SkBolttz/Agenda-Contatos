package br.com.Agenda.Contatos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Agenda.Contatos.DTO.AlterarContatoDTO;
import br.com.Agenda.Contatos.DTO.DeletarContatoDTO;
import br.com.Agenda.Contatos.Entity.Contato;
import br.com.Agenda.Contatos.Exception.ContatoNaoExisteException;
import br.com.Agenda.Contatos.Repository.ContatoRepository;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public void cadastrar(Contato contato) {

        contatoRepository.save(contato);

    }

    public String buscar(String nome) {

        Contato contato = contatoRepository.findByNome(nome);

        if(contato == null){
            return "Contato não encontrado!";
        }

        return "Contato: " + contato.getNome() + " - Telefone: " + contato.getTelefone() + " - Email: " + contato.getEmail();
    }

    public String buscarEmail(String email) throws ContatoNaoExisteException {
        
        Contato contato = contatoRepository.findByEmail(email);

        if(email == null){
            throw new ContatoNaoExisteException("Contato não encontrado!");
        }

        return "Contato: " + contato.getNome() + " - Telefone: " + contato.getTelefone() + " - Email: " + contato.getEmail();
    }

    public void atualizar(AlterarContatoDTO contato, String nomeContato) throws ContatoNaoExisteException {

        var usuario = contatoRepository.findByNome(nomeContato);

        if(usuario == null){
            throw new ContatoNaoExisteException("Contato não encontrado!");
        }

        if(contato.telefone() != null){
            usuario.setTelefone(contato.telefone());
        }

        if(contato.email() != null){
            usuario.setEmail(contato.email());
        }

        if(contato.nome() != null){
            usuario.setNome(contato.nome());
        }

        contatoRepository.save(usuario);
    }

    public void deletar(DeletarContatoDTO contato) throws ContatoNaoExisteException {
        
        var usuario = contatoRepository.findByNome(contato.nome());

        if(usuario == null){
            throw new ContatoNaoExisteException("Contato não encontrado!");
        }

        contatoRepository.delete(usuario);
    }

    public String listarContatos() {
        
        return contatoRepository.findAll()
        .stream()
        .map(contato -> "Contato: " + contato.getNome() + " - Telefone: " + contato.getTelefone() + " - Email: " + contato.getEmail())
        .reduce("", (a, b) -> a + "\n" + b);
    }

}

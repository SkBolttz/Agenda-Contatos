package br.com.Agenda.Contatos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Agenda.Contatos.DTO.AlterarContatoDTO;
import br.com.Agenda.Contatos.DTO.DeletarContatoDTO;
import br.com.Agenda.Contatos.Entity.Contato;
import br.com.Agenda.Contatos.Exception.ContatoNaoExisteException;
import br.com.Agenda.Contatos.Service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contato")
public class ContatoController {
    
    @Autowired
    private ContatoService contatoService;

    @Operation(summary = "Requisição para Cadastro do contato", description = "Requisição para Cadastro do contato")
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody @Valid Contato contato) {
        
        contatoService.cadastrar(contato);

        return ResponseEntity.status(200).body("Contato cadastrado com sucesso!");
    }

    @Operation(summary = "Requisição para buscar um contato pelo nome", description = "Requisição para Busca do contato")
    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<String> buscar(@PathVariable String nome) {
        String resultado = contatoService.buscar(nome);

        if (resultado == null) {
            return ResponseEntity.status(404).body("Contato não encontrado!");
        }

        return ResponseEntity.status(200).body(resultado);
    }

    @Operation(summary = "Requisição para buscar um contato pelo email", description = "Requisição para Busca do contato")
    @GetMapping("/buscar/email/{email}")
    public ResponseEntity<String> buscarEmail(@PathVariable String email) throws ContatoNaoExisteException {
        String resultado = contatoService.buscarEmail(email);

        if (resultado == null) {
            return ResponseEntity.status(404).body("Contato não encontrado!");
        }

        return ResponseEntity.status(200).body(resultado);
    }

    @Operation(summary = "Requisição para alterar um contato", description = "Requisição para alterar um contato, deve-se informar o nome do contato")
    @PutMapping("/alterar/{nomeContato}")
    public ResponseEntity<String> alterar(@RequestBody @Valid AlterarContatoDTO contato, @PathVariable String nomeContato) throws ContatoNaoExisteException {
        
        contatoService.atualizar(contato, nomeContato);

        return ResponseEntity.status(200).body("Contato alterado com sucesso!");
    }

    @Operation(summary = "Requisição para deletar um contato", description = "Requisição para deletar um contato")
    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletar(@RequestBody @Valid DeletarContatoDTO contato) throws ContatoNaoExisteException {
        
        contatoService.deletar(contato);

        return ResponseEntity.status(200).body("Contato deletado com sucesso!");
    }

    @Operation(summary = "Requisição para listar todos os contatos", description = "Requisição para listar todos os contatos")
    @GetMapping("/listar")
    public ResponseEntity<String> listar() {
        
        return ResponseEntity.status(200).body(contatoService.listarContatos());
    }
}

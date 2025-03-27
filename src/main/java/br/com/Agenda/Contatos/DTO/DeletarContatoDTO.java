package br.com.Agenda.Contatos.DTO;

import jakarta.validation.constraints.NotBlank;

public record DeletarContatoDTO(
    @NotBlank String nome
) {
    
}

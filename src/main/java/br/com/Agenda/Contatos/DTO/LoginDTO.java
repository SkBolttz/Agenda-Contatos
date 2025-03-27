package br.com.Agenda.Contatos.DTO;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    @NotBlank
    String nome,
    @NotBlank
    String password
) {
    
}

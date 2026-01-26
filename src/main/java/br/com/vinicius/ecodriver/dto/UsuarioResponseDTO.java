package br.com.vinicius.ecodriver.dto;

import br.com.vinicius.ecodriver.model.Role;
import br.com.vinicius.ecodriver.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        Role role
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getRole()
        );
    }
}

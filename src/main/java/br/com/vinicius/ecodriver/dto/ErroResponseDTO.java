package br.com.vinicius.ecodriver.dto;

import java.time.LocalDateTime;

public record ErroResponseDTO(
        LocalDateTime timestamp,
        Integer status,
        String mensagem,
        String path
) {

}

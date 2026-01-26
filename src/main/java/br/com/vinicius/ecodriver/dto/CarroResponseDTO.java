package br.com.vinicius.ecodriver.dto;


import br.com.vinicius.ecodriver.model.Carro;
import br.com.vinicius.ecodriver.model.StatusCarro;

public record CarroResponseDTO(
        Long id,
        String marca,
        String modelo,
        String placa,
        Double valorDiaria,
        StatusCarro status
) {
    public CarroResponseDTO(Carro carro) {
        this(
                carro.getId(),
                carro.getMarca(),
                carro.getModelo(),
                carro.getPlaca(),
                carro.getValorDiaria(),
                carro.getStatus()
        );
    }
}

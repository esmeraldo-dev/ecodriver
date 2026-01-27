package br.com.vinicius.ecodriver.service;

import br.com.vinicius.ecodriver.model.Carro;
import br.com.vinicius.ecodriver.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository carroRepository;

    public Carro salvarCarro(Carro carro) {
        carro.setPlaca(carro.getPlaca().toUpperCase());
        return carroRepository.save(carro);
    }

    public List<Carro> listarTodosOsCarros() {
        return carroRepository.findAll();
    }

    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com o ID: " + id));
    }

    public Carro atualizarCarroPorId(Long id, Carro carro) {
        Carro carroEntity = carroRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Carro não encontrado com o ID: " + id)
        );
        Carro carroAtualizado = Carro.builder()
                .marca(carro.getMarca() != null ? carro.getMarca() : carroEntity.getMarca())
                .modelo(carro.getModelo() != null ? carro.getModelo() : carroEntity.getModelo())
                .valorDiaria(carro.getValorDiaria() != null ? carro.getValorDiaria() : carroEntity.getValorDiaria())
                .placa(carro.getPlaca() != null ? carro.getPlaca().toUpperCase() : carroEntity.getPlaca())
                .status(carro.getStatus() != null ? carro.getStatus() : carroEntity.getStatus())
                .id(carroEntity.getId())
                .build();

        return carroRepository.saveAndFlush(carroAtualizado);
    }

    public void deletarCarroPorId(Long id) {
        if (!carroRepository.existsById(id)) {
            throw new RuntimeException("Carro não encontrado com o ID: " + id);
        }
        carroRepository.deleteById(id);
    }

}

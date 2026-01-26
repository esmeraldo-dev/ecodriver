package br.com.vinicius.ecodriver.service;

import br.com.vinicius.ecodriver.model.Carro;
import br.com.vinicius.ecodriver.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository carroRepository;

    public Carro salvar(Carro carro) {
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

}

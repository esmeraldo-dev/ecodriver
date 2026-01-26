package br.com.vinicius.ecodriver.service;

import br.com.vinicius.ecodriver.model.Aluguel;
import br.com.vinicius.ecodriver.model.Carro;
import br.com.vinicius.ecodriver.model.StatusCarro;
import br.com.vinicius.ecodriver.model.Usuario;
import br.com.vinicius.ecodriver.repository.AluguelRepository;
import br.com.vinicius.ecodriver.repository.CarroRepository;
import br.com.vinicius.ecodriver.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final CarroRepository carroRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Aluguel alugar(Long carroId, Long usuarioId) {
        Carro carro = carroRepository.findById(carroId)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com o ID: " + carroId));

        if (!carro.getStatus().equals(StatusCarro.DISPONIVEL)){
            throw new RuntimeException("Este carro não está disponível para locação no momento.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + usuarioId));

        Aluguel aluguel = new Aluguel();
        aluguel.setCarro(carro);
        aluguel.setUsuario(usuario);
        aluguel.setDataInicio(LocalDateTime.now());

        carro.setStatus(StatusCarro.ALUGADO);
        carroRepository.saveAndFlush(carro);

        return aluguelRepository.save(aluguel);
    }
}

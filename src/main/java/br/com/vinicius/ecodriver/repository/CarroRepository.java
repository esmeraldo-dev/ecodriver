package br.com.vinicius.ecodriver.repository;

import br.com.vinicius.ecodriver.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByUsuarioId(Long usuarioId);
}

package br.com.vinicius.ecodriver.repository;

import br.com.vinicius.ecodriver.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
}

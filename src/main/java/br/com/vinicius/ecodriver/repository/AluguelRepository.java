package br.com.vinicius.ecodriver.repository;

import br.com.vinicius.ecodriver.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository<Usuario, Long> {
}

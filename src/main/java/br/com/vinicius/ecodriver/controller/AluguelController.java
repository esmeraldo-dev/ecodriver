package br.com.vinicius.ecodriver.controller;

import br.com.vinicius.ecodriver.model.Aluguel;
import br.com.vinicius.ecodriver.service.AluguelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alugueis")
@RequiredArgsConstructor
public class AluguelController {

    private final AluguelService aluguelService;

    @PostMapping("/{carroId}/{usuarioId}")
    public ResponseEntity<Aluguel> realizarAluguel(@PathVariable Long carroId,
                                                   @PathVariable Long usuarioId) {
        return ResponseEntity.status(201).body(aluguelService.alugar(carroId, usuarioId));
    }

    @PutMapping("/devolver/{aluguelId}")
    public ResponseEntity<Aluguel> devolverCarro(@PathVariable Long aluguelId) {
        return ResponseEntity.ok(aluguelService.devolver(aluguelId));
    }
}

package br.com.vinicius.ecodriver.controller;

import br.com.vinicius.ecodriver.model.Carro;
import br.com.vinicius.ecodriver.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
@RequiredArgsConstructor
public class CarroController {

    private final CarroService carroService;

    @PostMapping
    public ResponseEntity<Carro> cadastrarCarro(@RequestBody Carro carro){
        return ResponseEntity.status(201).body(carroService.salvarCarro(carro));
    }
    @GetMapping
    public ResponseEntity<List<Carro>> listarCarro(){
        return ResponseEntity.ok(carroService.listarTodosOsCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarCarroPorId(@PathVariable Long id){
        return ResponseEntity.ok(carroService.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id,
                                                @RequestBody Carro carro){
        Carro atualiazado = carroService.atualizarCarroPorId(id, carro);
        return ResponseEntity.ok(atualiazado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarroPorId(@PathVariable Long id){
        carroService.deletarCarroPorId(id);
        return ResponseEntity.noContent().build();
    }
}

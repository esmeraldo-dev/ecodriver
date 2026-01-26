package br.com.vinicius.ecodriver.controller;

import br.com.vinicius.ecodriver.dto.CarroResponseDTO;
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
    public ResponseEntity<CarroResponseDTO> cadastrarCarro(@RequestBody Carro carro){
        Carro carroSalvo = carroService.salvarCarro(carro);
        return ResponseEntity.status(201).body(new CarroResponseDTO(carroSalvo));
    }
    @GetMapping
    public ResponseEntity<List<CarroResponseDTO>> listarCarro(){
        List<CarroResponseDTO> lista = carroService.listarTodosOsCarros()
                .stream()
                .map(CarroResponseDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> buscarCarroPorId(@PathVariable Long id){
        Carro carro = carroService.buscarPorId(id);
        return ResponseEntity.ok(new CarroResponseDTO(carro));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CarroResponseDTO> atualizarCarro(@PathVariable Long id,
                                                           @RequestBody Carro carro){
        Carro atualiazado = carroService.atualizarCarroPorId(id, carro);
        return ResponseEntity.ok(new CarroResponseDTO(atualiazado));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarroPorId(@PathVariable Long id){
        carroService.deletarCarroPorId(id);
        return ResponseEntity.noContent().build();
    }
}

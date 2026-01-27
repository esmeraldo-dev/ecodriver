package br.com.vinicius.ecodriver.controller;

import br.com.vinicius.ecodriver.dto.UsuarioResponseDTO;
import br.com.vinicius.ecodriver.model.Usuario;
import br.com.vinicius.ecodriver.service.UsuarioService;
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
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody Usuario usuario) {
        System.out.println("DEBUG - Email recebido: " + usuario.getEmail());
        Usuario salvo = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(201).body(new UsuarioResponseDTO(salvo));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosOsUsuarios() {
        List<UsuarioResponseDTO> usuario = usuarioService.listarTodosOsUsuarios()
                .stream()
                .map(UsuarioResponseDTO::new)
                .toList();

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> listarUsuariosPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id,
                                                               @RequestBody Usuario usuario) {
        Usuario atualizado = usuarioService.atualizarUsuarioPorId(id, usuario);
        return ResponseEntity.ok(new UsuarioResponseDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable Long id) {
        usuarioService.deletarUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }
}

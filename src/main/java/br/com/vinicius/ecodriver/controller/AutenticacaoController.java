package br.com.vinicius.ecodriver.controller;

import br.com.vinicius.ecodriver.dto.AutenticacaoDTO;
import br.com.vinicius.ecodriver.infra.TokenService;
import br.com.vinicius.ecodriver.model.Usuario;
import br.com.vinicius.ecodriver.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AutenticacaoDTO data) {
        var usuario = this.repository.findByEmail(data.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + data.email()));

        if (passwordEncoder.matches(data.senha(), usuario.getPassword())) {

            String token = tokenService.gerarToken((Usuario) usuario);

            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().body("Senha inválida");
    }
}

package vaccine.time.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccine.time.api.domain.dto.UsuarioDTO;
import vaccine.time.api.domain.service.UsuarioService;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioCadastrado = usuarioService.cadastrar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> listarUsuarios(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        Page<UsuarioDTO> usuarios = usuarioService.obterTodos(paginacao);
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<Optional<String>> excluirUsuario(@PathVariable Integer id) {
        Optional<String> usuarioExcluido = usuarioService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioExcluido);
    }
}

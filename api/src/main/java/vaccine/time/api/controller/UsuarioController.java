package vaccine.time.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vaccine.time.api.domain.dto.UsuarioDTO;
import vaccine.time.api.domain.model.Usuario;
import vaccine.time.api.domain.repository.UsuarioRepository;
import vaccine.time.api.domain.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public UsuarioDTO cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.cadastrar(usuarioDTO);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.obterTodos();
    }
}

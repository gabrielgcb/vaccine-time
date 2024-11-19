package vaccine.time.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<UsuarioDTO> listarUsuarios(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return usuarioService.obterTodos(paginacao);
    }
}

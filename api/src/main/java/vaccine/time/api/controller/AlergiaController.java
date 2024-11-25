package vaccine.time.api.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import vaccine.time.api.domain.dto.AlergiaDTO;
import vaccine.time.api.domain.model.Alergia;
import vaccine.time.api.domain.service.AlergiaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alergias")
public class AlergiaController {

    @Autowired
    private AlergiaService alergiaService;

    @PostMapping
    private AlergiaDTO cadastrarAlergia(@RequestBody AlergiaDTO alergiaDTO) {
        return alergiaService.cadastrar(alergiaDTO);
    }

    @GetMapping
    private Page<AlergiaDTO> listarAlergias(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return alergiaService.listar(paginacao);
    }

    @DeleteMapping(value = "/{id}")
    private Optional<String> excluirAlergia(@PathVariable Integer id) {
        return alergiaService.excluir(id);
    }
}

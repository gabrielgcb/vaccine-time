package vaccine.time.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccine.time.api.domain.dto.AlergiaDTO;
import vaccine.time.api.domain.service.AlergiaService;

import java.util.Optional;

@RestController
@RequestMapping("/alergias")
public class AlergiaController {

    @Autowired
    private AlergiaService alergiaService;

    @PostMapping
    private ResponseEntity<AlergiaDTO> cadastrarAlergia(@RequestBody AlergiaDTO alergiaDTO) {
        AlergiaDTO alergiaCadastrada = alergiaService.cadastrar(alergiaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alergiaCadastrada);
    }

    @GetMapping
    private ResponseEntity<Page<AlergiaDTO>> listarAlergias(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        Page<AlergiaDTO> alergias = alergiaService.listar(paginacao);
        return ResponseEntity.ok(alergias);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<Optional<String>> excluirAlergia(@PathVariable Integer id) {
        Optional<String> alergiaExcluida = alergiaService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alergiaExcluida);
    }
}

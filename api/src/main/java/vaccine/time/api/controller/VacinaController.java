package vaccine.time.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccine.time.api.domain.dto.VacinaDTO;
import vaccine.time.api.domain.service.VacinaService;

import java.util.Optional;

@RestController
@RequestMapping("/vacinas")
public class VacinaController {

    @Autowired
    private VacinaService vacinaService;

    @PostMapping
    private ResponseEntity<VacinaDTO> cadastrarVacina(@RequestBody VacinaDTO vacina) {
        VacinaDTO vacinaCadastrada = vacinaService.cadastrar(vacina);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinaCadastrada);
    }

    @GetMapping
    private ResponseEntity<Page<VacinaDTO>> listarVacinas(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        Page<VacinaDTO> vacinas = vacinaService.listar(paginacao);
        return ResponseEntity.ok(vacinas);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<Optional<String>> excluirVacina(@PathVariable Integer id) {
        Optional<String> vacinaExcluida = vacinaService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vacinaExcluida);
    }
}

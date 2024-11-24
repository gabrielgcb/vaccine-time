package vaccine.time.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private VacinaDTO cadastrarVacina(@RequestBody VacinaDTO vacina) {
        return vacinaService.cadastrar(vacina);
    }

    @GetMapping
    private Page<VacinaDTO> listarVacinas(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return vacinaService.listar(paginacao);
    }

    @DeleteMapping(value = "/{id}")
    private Optional<String> excluirVacina(@PathVariable Integer id) {
        return vacinaService.excluir(id);
    }
}

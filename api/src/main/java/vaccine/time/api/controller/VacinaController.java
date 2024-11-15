package vaccine.time.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vaccine.time.api.domain.model.Agenda;
import vaccine.time.api.domain.model.Vacina;
import vaccine.time.api.domain.service.AgendaService;
import vaccine.time.api.domain.service.VacinaService;

import java.util.List;

@RestController
@RequestMapping("/vacinas")
public class VacinaController {

    @Autowired
    private VacinaService vacinaService;

    @PostMapping
    private Vacina cadastrarVacina(@RequestBody Vacina vacina) {
        return vacinaService.cadastrar(vacina);
    }

    @GetMapping
    private List<Vacina> listarVacinas() {
        return vacinaService.listar();
    }
}

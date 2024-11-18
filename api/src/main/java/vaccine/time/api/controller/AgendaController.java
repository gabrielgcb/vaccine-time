package vaccine.time.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vaccine.time.api.domain.dto.AgendaDTO;
import vaccine.time.api.domain.model.Agenda;
import vaccine.time.api.domain.service.AgendaService;

import java.util.List;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    private AgendaDTO cadastrarAgenda(@RequestBody AgendaDTO agendaDTO) {
        return agendaService.cadastrar(agendaDTO);
    }

    @GetMapping
    private List<AgendaDTO> listarAgendas() {
        return agendaService.listar();
    }
}

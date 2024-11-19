package vaccine.time.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private Page<AgendaDTO> listarAgendas(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return agendaService.listar(paginacao);
    }
}

package vaccine.time.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccine.time.api.domain.dto.AgendaDTO;
import vaccine.time.api.domain.enums.SituacaoAgenda;
import vaccine.time.api.domain.service.AgendaService;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    private ResponseEntity<AgendaDTO> cadastrarAgenda(@RequestBody AgendaDTO agendaDTO) {
        AgendaDTO agendaCriada = agendaService.cadastrar(agendaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaCriada);
    }

    @GetMapping
    private ResponseEntity<Page<AgendaDTO>> listarAgendas(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        Page<AgendaDTO> agendas = agendaService.listar(paginacao);
        return ResponseEntity.ok(agendas);
    }

    @PutMapping("/{id}/situacao")
    public ResponseEntity<AgendaDTO> atualizarSituacao(@PathVariable Integer id, @RequestParam SituacaoAgenda novaSituacao) {
        AgendaDTO agendaAtualizada = agendaService.atualizar(id, novaSituacao);
        return ResponseEntity.ok(agendaAtualizada);
    }

    @GetMapping("/filtro")
    public ResponseEntity<Page<AgendaDTO>> listarComFiltros(
            @RequestParam(required = false) String usuarioNome,
            @RequestParam(required = false) String vacinaTitulo,
            Pageable pageable) {
        Page<AgendaDTO> agendasFiltradas = agendaService.listarComFiltros(usuarioNome, vacinaTitulo, pageable);
        return ResponseEntity.ok(agendasFiltradas);
    }

}

package vaccine.time.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccine.time.api.domain.dto.AgendaDTO;
import vaccine.time.api.domain.enums.SituacaoAgenda;
import vaccine.time.api.domain.model.Agenda;
import vaccine.time.api.domain.model.Usuario;
import vaccine.time.api.domain.model.Vacina;
import vaccine.time.api.domain.repository.AgendaRepository;
import vaccine.time.api.domain.service.agenda.AgendaAgendador;
import vaccine.time.api.domain.service.agenda.AgendaValidador;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private AgendaValidador agendaValidador;

    @Autowired
    private AgendaAgendador agendaAgendador;

    @Transactional
    public AgendaDTO cadastrar(AgendaDTO agendaDTO) {
        // Validações
        Usuario usuario = agendaValidador.validarUsuario(agendaDTO.usuario().id());
        Vacina vacina = agendaValidador.validarVacina(agendaDTO.vacina().id());

        // Primeira agenda
        Agenda primeiraAgenda = new Agenda();
        primeiraAgenda.setData(agendaDTO.data());
        primeiraAgenda.setHora(agendaDTO.hora());
        primeiraAgenda.setObservacoes(agendaDTO.observacoes());
        primeiraAgenda.setSituacao(SituacaoAgenda.AGENDADO);
        primeiraAgenda.setDataSituacao(agendaDTO.dataSituacao());
        primeiraAgenda.setUsuario(usuario);
        primeiraAgenda.setVacina(vacina);
        agendaRepository.save(primeiraAgenda);

        // Agendar próximas doses
        if (vacina.getDoses() > 1) {
            agendaAgendador.agendarProximasDoses(agendaDTO.data(), agendaDTO.hora(), usuario, vacina, agendaDTO.dataSituacao());
        }

        return new AgendaDTO(primeiraAgenda);
    }

    public Page<AgendaDTO> listar(Pageable paginacao) {
        Page<Agenda> pagina = agendaRepository.findAll(paginacao);
        return pagina.map(AgendaDTO::new);
    }


}

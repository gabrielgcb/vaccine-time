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
import vaccine.time.api.domain.repository.UsuarioRepository;
import vaccine.time.api.domain.repository.VacinaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VacinaRepository vacinaRepository;

    @Transactional
    public AgendaDTO cadastrar(AgendaDTO agendaDTO) {
        // Verificar existência do usuário
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(agendaDTO.usuario().id());
        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado com o id: " + agendaDTO.usuario().id());
        }
        Usuario usuario = usuarioOptional.get();

        // Verificar existência da vacina
        Optional<Vacina> vacinaOptional = vacinaRepository.findById(agendaDTO.vacina().id());
        if (vacinaOptional.isEmpty()) {
            throw new RuntimeException("Vacina não encontrada com o id: " + agendaDTO.vacina().id());
        }
        Vacina vacina = vacinaOptional.get();

        // Primeira agenda (data inicial fornecida pelo usuário)
        Agenda primeiraAgenda = new Agenda();
        primeiraAgenda.setData(agendaDTO.data());
        primeiraAgenda.setHora(agendaDTO.hora());
        primeiraAgenda.setObservacoes(agendaDTO.observacoes());
        primeiraAgenda.setSituacao(SituacaoAgenda.AGENDADO);
        primeiraAgenda.setDataSituacao(agendaDTO.dataSituacao());
        primeiraAgenda.setUsuario(usuario);
        primeiraAgenda.setVacina(vacina);
        agendaRepository.save(primeiraAgenda);

        // Criar agendas subsequentes com base em doses, periodicidade e intervalo
        if (vacina.getDoses() > 1) {
            LocalDate dataAtual = agendaDTO.data();
            for (int i = 1; i < vacina.getDoses(); i++) {
                dataAtual = calcularProximaData(dataAtual, vacina.getPeriodicidade(), vacina.getIntervalo());
                Agenda agendaSeguinte = new Agenda();
                agendaSeguinte.setData(dataAtual);
                agendaSeguinte.setHora(agendaDTO.hora());
                agendaSeguinte.setObservacoes((i + 1) + "ª dose");
                agendaSeguinte.setSituacao(SituacaoAgenda.AGENDADO);
                agendaSeguinte.setDataSituacao(agendaDTO.dataSituacao());
                agendaSeguinte.setUsuario(usuario);
                agendaSeguinte.setVacina(vacina);
                agendaRepository.save(agendaSeguinte);
            }
        }

        // Retornar a primeira agenda como DTO
        return new AgendaDTO(primeiraAgenda);
    }

    /**
     * Calcula a próxima data com base na periodicidade e intervalo.
     *
     * @param dataAtual     Data inicial
     * @param periodicidade Tipo de periodicidade (1 = dia, 2 = semana, 3 = meses, 4 = anos)
     * @param intervalo     Quantidade de unidades da periodicidade
     * @return Próxima data calculada
     */
    private LocalDate calcularProximaData(LocalDate dataAtual, int periodicidade, int intervalo) {
        switch (periodicidade) {
            case 1: // Dias
                return dataAtual.plusDays(intervalo);
            case 2: // Semanas
                return dataAtual.plusWeeks(intervalo);
            case 3: // Meses
                return dataAtual.plusMonths(intervalo);
            case 4: // Anos
                return dataAtual.plusYears(intervalo);
            default:
                throw new IllegalArgumentException("Periodicidade inválida: " + periodicidade);
        }
    }

    public Page<AgendaDTO> listar(Pageable paginacao) {
        Page<Agenda> pagina = agendaRepository.findAll(paginacao);
        return pagina.map(AgendaDTO::new);
    }
}

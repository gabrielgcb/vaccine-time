package vaccine.time.api.domain.service.agenda;

import org.springframework.stereotype.Component;
import vaccine.time.api.domain.enums.SituacaoAgenda;
import vaccine.time.api.domain.model.Agenda;
import vaccine.time.api.domain.model.Usuario;
import vaccine.time.api.domain.model.Vacina;
import vaccine.time.api.domain.repository.AgendaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class AgendaAgendador {

    private final AgendaRepository agendaRepository;

    public AgendaAgendador(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public void agendarProximasDoses(LocalDate dataInicial, LocalTime horario, Usuario usuario, Vacina vacina, LocalDate dataSituacao) {
        LocalDate dataAtual = dataInicial;

        for (int i = 1; i < vacina.getDoses(); i++) {
            dataAtual = calcularProximaData(dataAtual, vacina.getPeriodicidade(), vacina.getIntervalo());
            Agenda agenda = new Agenda();
            agenda.setData(dataAtual);
            agenda.setHora(horario);
            agenda.setObservacoes((i + 1) + "ª dose");
            agenda.setSituacao(SituacaoAgenda.AGENDADO);
            agenda.setDataSituacao(dataSituacao);
            agenda.setUsuario(usuario);
            agenda.setVacina(vacina);

            agendaRepository.save(agenda);
        }
    }

    private LocalDate calcularProximaData(LocalDate dataAtual, int periodicidade, int intervalo) {
        switch (periodicidade) {
            case 1:
                return dataAtual.plusDays(intervalo);
            case 2:
                return dataAtual.plusWeeks(intervalo);
            case 3:
                return dataAtual.plusMonths(intervalo);
            case 4:
                return dataAtual.plusYears(intervalo);
            default:
                throw new IllegalArgumentException("Periodicidade inválida: " + periodicidade);
        }
    }
}

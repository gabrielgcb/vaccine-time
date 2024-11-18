package vaccine.time.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import vaccine.time.api.domain.enums.SituacaoAgenda;
import vaccine.time.api.domain.model.Agenda;
import vaccine.time.api.domain.model.Usuario;
import vaccine.time.api.domain.model.Vacina;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendaDTO(
        Integer id,
        LocalDate data,
        LocalTime hora,
        SituacaoAgenda situacao,
        LocalDate dataSituacao,
        String observacoes,
        UsuarioDTO usuario,
        VacinaDTO vacina
) {

    public AgendaDTO(Agenda agenda) {
        this(
                agenda.getId(),
                agenda.getData(),
                agenda.getHora(),
                agenda.getSituacao(),
                agenda.getDataSituacao(),
                agenda.getObservacoes(),
                new UsuarioDTO(agenda.getUsuario()),
                new VacinaDTO(agenda.getVacina())
        );
    }
}

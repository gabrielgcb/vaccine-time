package vaccine.time.api.domain.dto;

import vaccine.time.api.domain.model.Vacina;

public record VacinaDTO(
        Integer id,
        String titulo,
        String descricao,
        Integer doses,
        Integer periodicidade,
        Integer intervalo
) {
    public VacinaDTO(Vacina vacina) {
        this(
                vacina.getId(),
                vacina.getTitulo(),
                vacina.getDescricao(),
                vacina.getDoses(),
                vacina.getPeriodicidade(),
                vacina.getIntervalo());
    }
}

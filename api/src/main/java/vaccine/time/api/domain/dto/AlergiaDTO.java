package vaccine.time.api.domain.dto;

import vaccine.time.api.domain.model.Alergia;

public record AlergiaDTO(
        Integer id,
        String nome
) {
    public AlergiaDTO(Alergia alergia) {
        this(
                alergia.getId(),
                alergia.getNome()
        );
    }
}

package vaccine.time.api.domain.dto;

import vaccine.time.api.domain.model.Agenda;
import vaccine.time.api.domain.model.Alergia;
import vaccine.time.api.domain.model.Endereco;
import vaccine.time.api.domain.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public record UsuarioDTO(
        Integer id,
        String nome,
        LocalDate dataNascimento,
        Character sexo,
        Endereco endereco,
        List<Alergia> alergias
) {
    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getSexo(),
                usuario.getEndereco(),
                usuario.getAlergias());
    }
}

package vaccine.time.api.domain.service.agenda;

import org.springframework.stereotype.Component;
import vaccine.time.api.domain.model.Usuario;
import vaccine.time.api.domain.model.Vacina;
import vaccine.time.api.domain.repository.UsuarioRepository;
import vaccine.time.api.domain.repository.VacinaRepository;

@Component
public class AgendaValidador {

    private final UsuarioRepository usuarioRepository;

    private final VacinaRepository vacinaRepository;

    public AgendaValidador(UsuarioRepository usuarioRepository, VacinaRepository vacinaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.vacinaRepository = vacinaRepository;
    }

    public Usuario validarUsuario(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + usuarioId));
    }

    public Vacina validarVacina(Integer vacinaId) {
        return vacinaRepository.findById(vacinaId)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada com o id: " + vacinaId));
    }
}

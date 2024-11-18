package vaccine.time.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccine.time.api.domain.dto.AgendaDTO;
import vaccine.time.api.domain.model.Agenda;
import vaccine.time.api.domain.model.Usuario;
import vaccine.time.api.domain.model.Vacina;
import vaccine.time.api.domain.repository.AgendaRepository;
import vaccine.time.api.domain.repository.UsuarioRepository;
import vaccine.time.api.domain.repository.VacinaRepository;

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
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(agendaDTO.usuario().id());
        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado com o id: " + agendaDTO.usuario().id());
        }
        Usuario usuario = usuarioOptional.get();

        Optional<Vacina> vacinaOptional = vacinaRepository.findById(agendaDTO.vacina().id());
        if (vacinaOptional.isEmpty()) {
            throw new RuntimeException("Vacina não encontrada com o id: " + agendaDTO.vacina().id());
        }
        Vacina vacina = vacinaOptional.get();

        Agenda agenda = new Agenda(agendaDTO);
        agenda.setUsuario(usuario);
        agenda.setVacina(vacina);

        agendaRepository.save(agenda);

        return new AgendaDTO(agenda);
    }

    public List<AgendaDTO> listar() {
        return agendaRepository.findAll().stream()
                .map(AgendaDTO::new)
                .collect(Collectors.toList());
    }
}

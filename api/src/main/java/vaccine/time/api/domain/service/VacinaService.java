package vaccine.time.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccine.time.api.domain.dto.VacinaDTO;
import vaccine.time.api.domain.model.Agenda;
import vaccine.time.api.domain.model.Vacina;
import vaccine.time.api.domain.repository.AgendaRepository;
import vaccine.time.api.domain.repository.VacinaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;

    @Transactional
    public VacinaDTO cadastrar(VacinaDTO vacinaDTO) {
        Vacina vacina = new Vacina(vacinaDTO);
        vacinaRepository.save(vacina);
        return new VacinaDTO(vacina);
    }

    public List<VacinaDTO> listar() {
        return vacinaRepository.findAll().stream()
                .map(VacinaDTO::new)
                .toList();
    }
}

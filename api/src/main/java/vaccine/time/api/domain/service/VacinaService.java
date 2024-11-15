package vaccine.time.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccine.time.api.domain.model.Agenda;
import vaccine.time.api.domain.model.Vacina;
import vaccine.time.api.domain.repository.AgendaRepository;
import vaccine.time.api.domain.repository.VacinaRepository;

import java.util.List;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;

    @Transactional
    public Vacina cadastrar(Vacina vacina) {
        return vacinaRepository.save(vacina);
    }

    public List<Vacina> listar() {
        return vacinaRepository.findAll();
    }
}

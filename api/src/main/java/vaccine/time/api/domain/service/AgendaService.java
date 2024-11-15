package vaccine.time.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccine.time.api.domain.model.Agenda;
import vaccine.time.api.domain.repository.AgendaRepository;

import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Transactional
    public Agenda cadastrar(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public List<Agenda> listar() {
        return agendaRepository.findAll();
    }
}

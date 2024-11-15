package vaccine.time.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccine.time.api.domain.model.Alergia;
import vaccine.time.api.domain.repository.AlergiaRepository;

import java.util.List;

@Service
public class AlergiaService {

    @Autowired
    private AlergiaRepository alergiaRepository;

    @Transactional
    public Alergia cadastrar(Alergia alergia) {
        return alergiaRepository.save(alergia);
    }

    public List<Alergia> listar() {
        return alergiaRepository.findAll();
    }
}

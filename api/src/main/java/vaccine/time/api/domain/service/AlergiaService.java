package vaccine.time.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccine.time.api.domain.dto.AlergiaDTO;
import vaccine.time.api.domain.model.Alergia;
import vaccine.time.api.domain.repository.AlergiaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlergiaService {

    @Autowired
    private AlergiaRepository alergiaRepository;

    @Transactional
    public AlergiaDTO cadastrar(AlergiaDTO alergiaDTO) {
        Alergia alergia = new Alergia(alergiaDTO);
        alergiaRepository.save(alergia);
        return new AlergiaDTO(alergia);
    }

    public Page<AlergiaDTO> listar(Pageable paginacao) {
        Page<Alergia> pagina = alergiaRepository.findAll(paginacao);
        return pagina.map(AlergiaDTO::new);
    }
}

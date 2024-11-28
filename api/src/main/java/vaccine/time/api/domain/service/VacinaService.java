package vaccine.time.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccine.time.api.domain.dto.VacinaDTO;
import vaccine.time.api.domain.model.Vacina;
import vaccine.time.api.domain.repository.VacinaRepository;

import java.util.Optional;

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

    public Page<VacinaDTO> listar(Pageable paginacao) {
        Page<Vacina> pagina = vacinaRepository.findAll(paginacao);
        return pagina.map(VacinaDTO::new);
    }

    @Transactional
    public Optional<String> excluir(Integer id) {
        Optional<Vacina> buscaId = vacinaRepository.findById(id);
        if(buscaId.isPresent()) {
            vacinaRepository.deleteById(id);
            return ("Vacina " + buscaId.get().getTitulo() + " excluída com sucesso").describeConstable();
        } else {
            return "Id não encontrado".describeConstable();
        }
    }
}

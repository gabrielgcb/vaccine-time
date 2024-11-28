package vaccine.time.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccine.time.api.domain.dto.UsuarioDTO;
import vaccine.time.api.domain.model.Alergia;
import vaccine.time.api.domain.model.Usuario;
import vaccine.time.api.domain.repository.AlergiaRepository;
import vaccine.time.api.domain.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlergiaRepository alergiaRepository;

    @Transactional
    public UsuarioDTO cadastrar(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.alergias() != null && !usuarioDTO.alergias().isEmpty()) {
            for (Alergia alergia : usuarioDTO.alergias()) {
                if (alergia.getId() != null) {
                    Alergia alergiaExistente = alergiaRepository.findById(alergia.getId()).orElse(null);
                    if (alergiaExistente != null) {
                        alergia.setNome(alergiaExistente.getNome());
                    }
                }
            }
        }
        Usuario usuario = new Usuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
    }

    public Page<UsuarioDTO> obterTodos(Pageable paginacao) {
        Page<Usuario> pagina = usuarioRepository.findAll(paginacao);
        return pagina.map(UsuarioDTO::new);
    }

    @Transactional
    public Optional<String> excluir(Integer id) {
        Optional<Usuario> buscaId = usuarioRepository.findById(id);
        if(buscaId.isPresent()) {
            usuarioRepository.deleteById(id);
            return ("Usuario " + buscaId.get().getNome() + " excluído com sucesso").describeConstable();
        } else {
            return "Id não encontrado".describeConstable();
        }
    }
}

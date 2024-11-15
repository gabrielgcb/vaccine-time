package vaccine.time.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccine.time.api.domain.model.Alergia;
import vaccine.time.api.domain.model.Usuario;
import vaccine.time.api.domain.repository.AlergiaRepository;
import vaccine.time.api.domain.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlergiaRepository alergiaRepository;

    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        if (usuario.getAlergias() != null && !usuario.getAlergias().isEmpty()) {
            for (Alergia alergia : usuario.getAlergias()) {
                if (alergia.getId() != null) {
                    Alergia alergiaExistente = alergiaRepository.findById(alergia.getId()).orElse(null);
                    if (alergiaExistente != null) {
                        alergia.setNome(alergiaExistente.getNome());
                    }
                }
            }
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obterTodos() {
        return usuarioRepository.findAll();
    }
}

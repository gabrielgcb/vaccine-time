package vaccine.time.api.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vaccine.time.api.domain.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    @Query("SELECT a FROM Agenda a WHERE " +
            "(:usuarioNome IS NULL OR a.usuario.nome LIKE %:usuarioNome%) AND " +
            "(:vacinaTitulo IS NULL OR a.vacina.titulo LIKE %:vacinaTitulo%)")
    Page<Agenda> buscarComFiltros(@Param("usuarioNome") String usuarioNome,
                                  @Param("vacinaTitulo") String vacinaTitulo,
                                  Pageable pageable);
}

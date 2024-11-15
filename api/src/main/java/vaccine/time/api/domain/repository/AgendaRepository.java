package vaccine.time.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vaccine.time.api.domain.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
}

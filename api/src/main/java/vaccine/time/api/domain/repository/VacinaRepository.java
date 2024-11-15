package vaccine.time.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vaccine.time.api.domain.model.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Integer> {
}

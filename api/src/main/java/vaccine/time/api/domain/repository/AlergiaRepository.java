package vaccine.time.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vaccine.time.api.domain.model.Alergia;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Integer> {
}

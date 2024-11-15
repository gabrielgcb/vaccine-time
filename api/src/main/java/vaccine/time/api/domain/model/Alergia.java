package vaccine.time.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_alergias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Alergia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
}

package vaccine.time.api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import vaccine.time.api.domain.dto.AlergiaDTO;

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

    public Alergia(AlergiaDTO alergiaDTO) {
        this.id = alergiaDTO.id();
        this.nome = alergiaDTO.nome();
    }
}

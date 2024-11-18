package vaccine.time.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vaccine.time.api.domain.dto.VacinaDTO;

import java.util.List;

@Entity
@Table(name = "tb_vacinas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Vacina {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    private Integer doses;
    private Integer periodicidade;
    private Integer intervalo;

    @OneToMany(mappedBy = "vacina")
    private List<Agenda> agendas;

    public Vacina(VacinaDTO vacinaDTO) {
        this.titulo = vacinaDTO.titulo();
        this.descricao = vacinaDTO.descricao();
        this.doses = vacinaDTO.doses();
        this.periodicidade = vacinaDTO.periodicidade();
        this.intervalo = vacinaDTO.intervalo();
    }
}

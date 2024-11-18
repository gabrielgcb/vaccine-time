package vaccine.time.api.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import vaccine.time.api.domain.dto.AgendaDTO;
import vaccine.time.api.domain.enums.SituacaoAgenda;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "tb_agendas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agenda {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate data;
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private SituacaoAgenda situacao;

    @Column(name = "data_situacao")
    private LocalDate dataSituacao;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "vacina_id")
    private Vacina vacina;

    public Agenda(AgendaDTO agendaDTO) {
        this.data = agendaDTO.data();
        this.hora = agendaDTO.hora();
        this.situacao = SituacaoAgenda.AGENDADO;
        this.dataSituacao = agendaDTO.dataSituacao();
        this.observacoes = agendaDTO.observacoes();
        this.usuario = new Usuario(agendaDTO.usuario());
        this.vacina = new Vacina(agendaDTO.vacina());
    }
}

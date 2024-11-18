package vaccine.time.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vaccine.time.api.domain.dto.UsuarioDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    private Character sexo;

    @Embedded
    private Endereco endereco;

    @ManyToMany
    @JoinTable(
            name = "usuario_alergia",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "alergia_id")
    )
    private List<Alergia> alergias;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Agenda> agendas;

    public Usuario(UsuarioDTO usuarioDTO) {
        this.nome = usuarioDTO.nome();
        this.dataNascimento = usuarioDTO.dataNascimento();
        this.sexo = usuarioDTO.sexo();
        this.endereco = usuarioDTO.endereco();
        this.alergias = usuarioDTO.alergias();
    }
}

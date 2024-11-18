package vaccine.time.api.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vaccine.time.api.domain.dto.AlergiaDTO;
import vaccine.time.api.domain.model.Alergia;
import vaccine.time.api.domain.service.AlergiaService;

import java.util.List;

@RestController
@RequestMapping("/alergias")
public class AlergiaController {

    @Autowired
    private AlergiaService alergiaService;

    @PostMapping
    private AlergiaDTO cadastrarAlergia(@RequestBody AlergiaDTO alergiaDTO) {
        return alergiaService.cadastrar(alergiaDTO);
    }

    @GetMapping
    private List<AlergiaDTO> listarAlergias() {
        return alergiaService.listar();
    }
}

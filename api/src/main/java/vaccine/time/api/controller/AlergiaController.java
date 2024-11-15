package vaccine.time.api.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vaccine.time.api.domain.model.Alergia;
import vaccine.time.api.domain.service.AlergiaService;

import java.util.List;

@RestController
@RequestMapping("/alergias")
public class AlergiaController {

    @Autowired
    private AlergiaService alergiaService;

    @PostMapping
    private Alergia cadastrarAlergia(@RequestBody Alergia alergia) {
        return alergiaService.cadastrar(alergia);
    }

    @GetMapping
    private List<Alergia> listarAlergias() {
        return alergiaService.listar();
    }
}

package API.REST.Zoologico.Zoologico.API.Controller;

import API.REST.Zoologico.Zoologico.API.Model.Cuidador;
import API.REST.Zoologico.Zoologico.API.Repository.CuidadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cuidador")
public class CuidadorController {
    private CuidadorRepository cuidadorRepository;

    public CuidadorController(CuidadorRepository cuidadorRepository) {
        this.cuidadorRepository = cuidadorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Cuidador>> findAll() {
        return ResponseEntity.ok(cuidadorRepository.findAll());
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Cuidador>> findAll(@RequestParam(required = false) String especialidade, @RequestParam(required = false) String turno) {
        if (especialidade != null) {
            return ResponseEntity.ok(cuidadorRepository.findByEspecialidadeIgnoreCase(especialidade));
        }
        if (turno != null)  {
            return ResponseEntity.ok(cuidadorRepository.findByTurnoIgnoreCase(turno));
        }
        return ResponseEntity.ok(cuidadorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuidador> get(@PathVariable Long id) {
        return cuidadorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Cuidador> create(@RequestBody Cuidador cuidador) {
        return ResponseEntity.ok(cuidadorRepository.save(cuidador));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cuidador> update(@PathVariable Long id, @RequestBody Cuidador cuidador) {
        return cuidadorRepository.findById(id).map(existing -> {
            cuidador.setId(id);
            return ResponseEntity.ok(cuidadorRepository.save(cuidador));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuidadorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


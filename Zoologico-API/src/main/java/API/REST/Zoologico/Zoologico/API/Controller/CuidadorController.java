package API.REST.Zoologico.Zoologico.API.Controller;

import API.REST.Zoologico.Zoologico.API.Model.Cuidador;
import API.REST.Zoologico.Zoologico.API.Repository.CuidadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/cuidadores")
public class CuidadorController {
    private final CuidadorRepository repository;


    public CuidadorController(CuidadorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Cuidador> create(@RequestBody Cuidador cuidador) {
        return ResponseEntity.ok(repository.save(cuidador));
    }

    @GetMapping
    public ResponseEntity<List<Cuidador>> list(@RequestParam(required = false) String especialidade,
                                               @RequestParam(required = false) String turno) {
        if (especialidade != null) return ResponseEntity.ok(repository.findByEspecialidadeIgnoreCase(especialidade));
        if (turno != null) return ResponseEntity.ok(repository.findByTurnoIgnoreCase(turno));
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuidador> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuidador> update(@PathVariable Long id, @RequestBody Cuidador cuidador) {
        return repository.findById(id).map(existing -> {
            cuidador.setId(id);
            return ResponseEntity.ok(repository.save(cuidador));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


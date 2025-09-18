package API.REST.Zoologico.Zoologico.API.Controller;

import API.REST.Zoologico.Zoologico.API.Model.Habitat;
import API.REST.Zoologico.Zoologico.API.Repository.HabitatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/habitats")
public class HabitatController {
    private final HabitatRepository habitatRepository;


    public HabitatController(HabitatRepository habitatRepository) {
        this.habitatRepository = habitatRepository;
    }

    @PostMapping
    public ResponseEntity<Habitat> create(@RequestBody Habitat habitat) {
        return ResponseEntity.ok(habitatRepository.save(habitat));
    }

    @GetMapping
    public ResponseEntity<List<Habitat>> list(@RequestParam(required = false) String tipo) {
        if (tipo != null) return ResponseEntity.ok(habitatRepository.findByTipoIgnoreCase(tipo));
        return ResponseEntity.ok(habitatRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitat> get(@PathVariable Long id) {
        return habitatRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitat> update(@PathVariable Long id, @RequestBody Habitat habitat) {
        return habitatRepository.findById(id).map(existing -> {
            habitat.setId(id);
            return ResponseEntity.ok(habitatRepository.save(habitat));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        habitatRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

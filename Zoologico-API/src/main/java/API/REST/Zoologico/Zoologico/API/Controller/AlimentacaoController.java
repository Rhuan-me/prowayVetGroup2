package API.REST.Zoologico.Zoologico.API.Controller;

import API.REST.Zoologico.Zoologico.API.Model.Alimentacao;
import API.REST.Zoologico.Zoologico.API.Model.Cuidador;
import API.REST.Zoologico.Zoologico.API.Repository.AlimentacaoRepository;
import API.REST.Zoologico.Zoologico.API.Repository.CuidadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/alimentacao")
public class AlimentacaoController {
    private final AlimentacaoRepository alimentacaoRepository;


    public AlimentacaoController(AlimentacaoRepository alimentacaoRepository) {
        this.alimentacaoRepository = alimentacaoRepository;
    }

    @PostMapping
    public ResponseEntity<Alimentacao> create(@RequestBody Alimentacao alimentacao) {
        return ResponseEntity.ok(alimentacaoRepository.save(alimentacao));
    }

    @GetMapping
    public ResponseEntity<List<Alimentacao>> list(@RequestParam(required = false) String especialidade,
                                               @RequestParam(required = false) String turno) {
        if (especialidade != null) return ResponseEntity.ok(alimentacaoRepository.findByEspecialidadeIgnoreCase(especialidade));
        if (turno != null) return ResponseEntity.ok(alimentacaoRepository.findByTurnoIgnoreCase(turno));
        return ResponseEntity.ok(alimentacaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimentacao> get(@PathVariable Long id) {
        return alimentacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alimentacao> update(@PathVariable Long id, @RequestBody Alimentacao alimentacao) {
        return alimentacaoRepository.findById(id).map(existing -> {
            alimentacao.setId(id);
            return ResponseEntity.ok(alimentacaoRepository.save(alimentacao));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alimentacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


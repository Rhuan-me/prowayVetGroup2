package API.REST.Zoologico.Zoologico.API.Controller;

import API.REST.Zoologico.Zoologico.API.Model.Alimentacao;
import API.REST.Zoologico.Zoologico.API.Repository.AlimentacaoRepository;
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
    public ResponseEntity<List<Alimentacao>> list() {
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
package API.REST.Zoologico.Zoologico.API.Controller;

import API.REST.Zoologico.Zoologico.API.Model.Animal;
import API.REST.Zoologico.Zoologico.API.Service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<List<Animal>> listar(@RequestParam(required = false) String nome) {
        if (nome != null && !nome.isEmpty()) {
            return ResponseEntity.ok(animalService.findByNome(nome));
        }
        return ResponseEntity.ok(animalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Animal> criar(@RequestBody Animal animal) {
        Animal novoAnimal = animalService.create(animal);
        return new ResponseEntity<>(novoAnimal, HttpStatus.CREATED);
    }

    @PostMapping("/lote")
    public ResponseEntity<List<Animal>> criarEmLote(@RequestBody List<Animal> animais) {
        List<Animal> novosAnimais = animalService.createAll(animais);
        return new ResponseEntity<>(novosAnimais, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizar(@PathVariable Long id, @RequestBody Animal animalAtualizado) {
        return ResponseEntity.ok(animalService.update(id, animalAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Animal>> buscarPorEspecie(@PathVariable String especie) {
        return ResponseEntity.ok(animalService.findByEspecie(especie));
    }

    @GetMapping("/idade")
    public ResponseEntity<List<Animal>> buscarPorIdade(@RequestParam int min, @RequestParam int max) {
        return ResponseEntity.ok(animalService.findByIdade(min, max));
    }
}
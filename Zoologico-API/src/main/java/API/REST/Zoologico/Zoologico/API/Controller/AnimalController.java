package API.REST.Zoologico.Zoologico.API.Controller;

import API.REST.Zoologico.Zoologico.API.DTos.AnimalDto;
import API.REST.Zoologico.Zoologico.API.Model.Animal;
import API.REST.Zoologico.Zoologico.API.Model.Cuidador;
import API.REST.Zoologico.Zoologico.API.Model.Habitat;
import API.REST.Zoologico.Zoologico.API.Service.AnimalService;
import API.REST.Zoologico.Zoologico.API.Service.CuidadorService;
import API.REST.Zoologico.Zoologico.API.Service.HabitatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private AnimalService animalService;
    private CuidadorService cuidadorService;
    private HabitatService habitatService;
//
    public AnimalController(AnimalService animalService, CuidadorService cuidadorService, HabitatService habitatService) {
        this.animalService = animalService;
        this.cuidadorService = cuidadorService;
        this.habitatService = habitatService;
    }

    @GetMapping
    public ResponseEntity<List<Animal>> findAll() {
        return ResponseEntity.ok(animalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Animal> criar(@RequestBody AnimalDto animalDto) {
        Cuidador cuidador = cuidadorService.findById(animalDto.cuidador_id());
        Habitat habitat = habitatService.findById(animalDto.habitat_id());

        Animal animal = new Animal();
        animal.setNome(animalDto.nome());
        animal.setEspecie(animalDto.especie());
        animal.setIdade(animalDto.idade());
        animal.setCuidador(cuidador);
        animal.setHabitat(habitat);

        Animal novoAnimal = animalService.create(animal);

        return new ResponseEntity<>(novoAnimal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizar(@PathVariable Long id, @RequestBody AnimalDto animalAtualizado) {
        return ResponseEntity.ok(animalService.update(id, animalAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/especie")
    public ResponseEntity<List<Animal>> buscarPorEspecie(@RequestParam String especie) {
        return ResponseEntity.ok(animalService.findByEspecie(especie));
    }

    @GetMapping("/idade")
    public ResponseEntity<List<Animal>> buscarPorIdade(@RequestParam int idadeMin, @RequestParam int idadeMax) {
        return ResponseEntity.ok(animalService.findByIdade(idadeMin, idadeMax));
    }
}
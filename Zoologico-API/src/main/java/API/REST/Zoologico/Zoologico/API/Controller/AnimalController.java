package API.REST.Zoologico.Zoologico.API.Controller;

import API.REST.Zoologico.Zoologico.API.Model.Animal;
import API.REST.Zoologico.Zoologico.API.Repository.AnimalRepository;
import API.REST.Zoologico.Zoologico.API.Service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping
    public List<Animal> listar() {
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Animal buscarPorId(@PathVariable Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado!"));
    }

    @PostMapping
    public Animal criar(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @PostMapping("/lote")
    public List<Animal> criarEmLote(@RequestBody List<Animal> animais) {
        return animalRepository.saveAll(animais);
    }

    @PutMapping("/{id}")
    public Animal atualizar(@PathVariable Long id, @RequestBody Animal animalAtualizado) {
        Animal existente = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado!"));

        existente.setNome(animalAtualizado.getNome());
        existente.setEspecie(animalAtualizado.getEspecie());
        existente.setIdade(animalAtualizado.getIdade());
        existente.setAlimentacao(animalAtualizado.getAlimentacao());
        existente.setCuidadores(animalAtualizado.getCuidadores());

        return animalRepository.save(existente);
    }

    // 🔹 Deletar animal
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado!"));
        animalRepository.delete(animal);
    }


    @GetMapping("/especie/{especie}")
    public List<Animal> buscarPorEspecie(@PathVariable String especie) {
        return animalRepository.findByEspecieIgnoreCase(especie);
    }

    @GetMapping("/idade")
    public List<Animal> buscarPorIdade(@RequestParam int min, @RequestParam int max) {
        return animalRepository.findByIdade(min, max);
    }

}

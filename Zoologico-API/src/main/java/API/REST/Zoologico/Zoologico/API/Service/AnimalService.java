package API.REST.Zoologico.Zoologico.API.Service;

import API.REST.Zoologico.Zoologico.API.DTos.AnimalDto;
import API.REST.Zoologico.Zoologico.API.Model.Animal;
import API.REST.Zoologico.Zoologico.API.Model.Habitat;
import API.REST.Zoologico.Zoologico.API.Repository.AnimalRepository;
import API.REST.Zoologico.Zoologico.API.Repository.CuidadorRepository;
import API.REST.Zoologico.Zoologico.API.Repository.HabitatRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final HabitatRepository habitatRepository;
    private final CuidadorRepository cuidadorRepository;

    public AnimalService(AnimalRepository animalRepository, HabitatRepository habitatRepository, CuidadorRepository cuidadorRepository) {
        this.animalRepository = animalRepository;
        this.habitatRepository = habitatRepository;
        this.cuidadorRepository = cuidadorRepository;
    }

    public Animal create(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal update(Long id, AnimalDto animalAtualizado) {
        Animal animalExistente = findById(id);
        Habitat habitatExistente = habitatRepository.findById(animalAtualizado.habitat_id())
                .orElseThrow(() -> new RuntimeException("Habitat não encontrado"));
        var cuidadorExistente = cuidadorRepository.findById(animalAtualizado.cuidador_id())
                .orElseThrow(() -> new RuntimeException("Cuidador não encontrado"));

        animalExistente.setNome(animalAtualizado.nome());
        animalExistente.setEspecie(animalAtualizado.especie());
        animalExistente.setIdade(animalAtualizado.idade());

        animalExistente.setHabitat(habitatExistente);
        animalExistente.setCuidador(cuidadorExistente);

        return animalRepository.save(animalExistente);
    }

    public List<Animal> findAll(){
        return animalRepository.findAll();
    }

    public Animal findById(Long id){
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado!"));
    }

    public void delete(Long id){
        Animal animal  = findById(id);
        animalRepository.delete(animal);
    }

    public List<Animal> findByEspecie(String especie) {
        return animalRepository.findByEspecieIgnoreCase(especie);
    }

    public List<Animal> findByIdade(int idadeMin, int idadeMax) {
        return animalRepository.findByIdade(idadeMin, idadeMax);
    }

    public List<Animal> findByNome(String nome) {
        return animalRepository.findByNomeContainingIgnoreCase(nome);
    }
}
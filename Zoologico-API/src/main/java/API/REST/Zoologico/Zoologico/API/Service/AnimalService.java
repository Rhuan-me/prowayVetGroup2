package API.REST.Zoologico.Zoologico.API.Service;

import API.REST.Zoologico.Zoologico.API.Model.Animal;
import API.REST.Zoologico.Zoologico.API.Model.Habitat;
import API.REST.Zoologico.Zoologico.API.Model.Veterinario;
import API.REST.Zoologico.Zoologico.API.Repository.AnimalRepository;
import API.REST.Zoologico.Zoologico.API.Repository.HabitatRepository;
import API.REST.Zoologico.Zoologico.API.Repository.VeterinarioRepository;

import java.util.List;

public class AnimalService {
    private AnimalRepository animalRepository;
    private HabitatRepository habitatRepository;

    public AnimalService(AnimalRepository animalRepository, HabitatRepository habitatRepository) {
        this.animalRepository = animalRepository;
        this.habitatRepository = habitatRepository;
    }

    public List<Animal> findAll(){
        return animalRepository.findAll();
    }

    public List<Animal> createAll(List<Animal> animal){
        return animalRepository.saveAll(animal);

    }

    public Animal create(Animal animal) {
        Habitat habitat = habitatRepository.findById(animal.getHabitat().getId())
                .orElseThrow(() -> new IllegalArgumentException("Habitat não encontrado"));

        long quantidadeAnimais = animalRepository.countByHabitat(habitat);

        if (quantidadeAnimais >= habitat.getCapacidade()) {
            throw new IllegalStateException(
                    "O habitat '" + habitat.getNome() + "' já atingiu sua capacidade máxima de "
                            + habitat.getCapacidade() + " animais."
            );
        }
        return animalRepository.save(animal);
    }

    public Animal findById(Long id){
        return animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal não encontrado!"));
    }

    public Animal update(Long id, Animal animalAtualizado){
        Animal animal = findById(id);

        animal.setNome(animalAtualizado.getNome());
        animal.setEspecie(animalAtualizado.getEspecie());
        animal.setIdade(animalAtualizado.getIdade());
        animal.setAlimentacao(animalAtualizado.getAlimentacao());
        animal.setCuidadores(animalAtualizado.getCuidadores());

        return animalRepository.save(animal);
    }

    public void delete(Long id){
        Animal animal =  findById(id);
        animalRepository.delete(animal);
    }

    public List<Animal> findByEspecie(String especie) {
        return animalRepository.findByEspecieIgnoreCase(especie);
    }

    public List<Animal> findByIdade(int idadeMin, int idadeMax) {
        return animalRepository.findByIdade(idadeMin, idadeMax);
    }
}

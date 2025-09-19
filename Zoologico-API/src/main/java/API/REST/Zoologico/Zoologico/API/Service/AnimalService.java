package API.REST.Zoologico.Zoologico.API.Service;

import API.REST.Zoologico.Zoologico.API.Model.Animal;
import API.REST.Zoologico.Zoologico.API.Model.Cuidador;
import API.REST.Zoologico.Zoologico.API.Model.Habitat;
import API.REST.Zoologico.Zoologico.API.Repository.AnimalRepository;
import API.REST.Zoologico.Zoologico.API.Repository.CuidadorRepository;
import API.REST.Zoologico.Zoologico.API.Repository.HabitatRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final HabitatRepository habitatRepository;
    private final CuidadorRepository cuidadorRepository; // Adicionado

    // Construtor atualizado
    public AnimalService(AnimalRepository animalRepository, HabitatRepository habitatRepository, CuidadorRepository cuidadorRepository) {
        this.animalRepository = animalRepository;
        this.habitatRepository = habitatRepository;
        this.cuidadorRepository = cuidadorRepository;
    }

    public Animal create(Animal animal) {
        validateAndPrepareAnimal(animal);
        return animalRepository.save(animal);
    }

    public Animal update(Long id, Animal animalAtualizado) {
        Animal animalExistente = findById(id);
        validateAndPrepareAnimal(animalAtualizado);

        animalExistente.setNome(animalAtualizado.getNome());
        animalExistente.setEspecie(animalAtualizado.getEspecie());
        animalExistente.setIdade(animalAtualizado.getIdade());
        animalExistente.setHabitat(animalAtualizado.getHabitat());
        animalExistente.setCuidadores(animalAtualizado.getCuidadores());

        return animalRepository.save(animalExistente);
    }

    /**
     * Método centralizado para validar e preparar um animal para ser salvo.
     */
    private void validateAndPrepareAnimal(Animal animal) {
        if (animal.getHabitat() == null || animal.getHabitat().getId() == null) {
            throw new IllegalArgumentException("O ID do habitat é obrigatório.");
        }

        // 1. Valida e carrega o Habitat
        Habitat habitat = habitatRepository.findById(animal.getHabitat().getId())
                .orElseThrow(() -> new IllegalArgumentException("Habitat com ID " + animal.getHabitat().getId() + " não encontrado."));
        animal.setHabitat(habitat);

        // 2. Valida a capacidade do Habitat
        long quantidadeAnimais = animalRepository.countByHabitat(habitat);
        if (animal.getId() == null && quantidadeAnimais >= habitat.getCapacidade()) { // Só checa para novos animais
            throw new IllegalStateException("O habitat '" + habitat.getNome() + "' já atingiu sua capacidade máxima.");
        }

        // 3. Valida e carrega os Cuidadores
        if (animal.getCuidadores() == null || animal.getCuidadores().isEmpty()) {
            throw new IllegalArgumentException("O animal deve ter pelo menos um cuidador.");
        }

        Set<Cuidador> cuidadoresGerenciados = new HashSet<>();
        for (Cuidador c : animal.getCuidadores()) {
            Cuidador cuidador = cuidadorRepository.findById(c.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Cuidador com ID " + c.getId() + " não encontrado."));
            cuidadoresGerenciados.add(cuidador);
        }
        animal.setCuidadores(cuidadoresGerenciados);
    }


    // --- MÉTODOS EXISTENTES (sem alteração) ---

    public List<Animal> findAll(){
        return animalRepository.findAll();
    }

    public Animal findById(Long id){
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado!"));
    }

    public void delete(Long id){
        animalRepository.deleteById(id);
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

    public List<Animal> createAll(List<Animal> animais){
        for (Animal animal : animais) {
            validateAndPrepareAnimal(animal);
        }
        return animalRepository.saveAll(animais);
    }
}
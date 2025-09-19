package API.REST.Zoologico.Zoologico.API.Service;

import API.REST.Zoologico.Zoologico.API.Model.Habitat;
import API.REST.Zoologico.Zoologico.API.Repository.HabitatRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class HabitatService {
    private HabitatRepository habitatRepository;


    public HabitatService(HabitatRepository habitatRepository) {
        this.habitatRepository = habitatRepository;
    }


    public Habitat save(Habitat habitat) {
        return habitatRepository.save(habitat);
    }


    public List<Habitat> findAll() {
        return habitatRepository.findAll();
    }


    public Habitat findById(Long id) {
        return habitatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitat não encontrado com id " + id));
    }


    public void delete(Long id) {
        habitatRepository.deleteById(id);
    }


    public List<Habitat> findByTipo(String tipo) {
        return habitatRepository.findByTipoIgnoreCase(tipo);
    }
}
package API.REST.Zoologico.Zoologico.API.Service;

import API.REST.Zoologico.Zoologico.API.Model.Veterinario;
import API.REST.Zoologico.Zoologico.API.Repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioService {
    private VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public List<Veterinario> findAll(){
        return veterinarioRepository.findAll();
    }
    public List<Veterinario> createAll(List<Veterinario> veterinarios){
        return veterinarioRepository.saveAll(veterinarios);

    }
    public Veterinario create(Veterinario veterinario){
        return veterinarioRepository.save(veterinario);
    }
    public Veterinario findById(Long id){
        return veterinarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Veterinário não encontrado!"));
    }
    public Veterinario update(Long id, Veterinario veterinarioAtualizado){
        Veterinario veterinario = findById(id);

        veterinario.setNome(veterinarioAtualizado.getNome());
        veterinario.setCrmv(veterinarioAtualizado.getCrmv());
        veterinario.setEspecialidade(veterinarioAtualizado.getEspecialidade());

        return veterinarioRepository.save(veterinario);
    }
    public void delete(Long id){
        Veterinario veterinario =  findById(id);
        veterinarioRepository.delete(veterinario);
    }
}

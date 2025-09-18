package API.REST.Zoologico.Zoologico.API.Service;

import API.REST.Zoologico.Zoologico.API.Model.Cuidador;
import API.REST.Zoologico.Zoologico.API.Repository.CuidadorRepository;


import java.util.List;

public class CuidadorService {
    private CuidadorRepository cuidadorRepository;

    public CuidadorService(CuidadorRepository cuidadorRepository) {
        this.cuidadorRepository = cuidadorRepository;
    }

    public List<Cuidador> findAll(){
        return cuidadorRepository.findAll();
    }
    public List<Cuidador> createAll(List<Cuidador> cuidadors){
        return cuidadorRepository.saveAll(cuidadors);
    }
    public Cuidador create(Cuidador cuidador){
        return cuidadorRepository.save(cuidador);
    }
    public Cuidador findById(Long id){
        return cuidadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Veterinário não encontrado!"));
    }
    public Cuidador update(Long id, Cuidador cuidadorAtualizado){
        Cuidador cuidador = findById(id);

        cuidador.setNome(cuidadorAtualizado.getNome());
        cuidador.setEspecialidade(cuidadorAtualizado.getEspecialidade());
        cuidador.setTurno(cuidadorAtualizado.getTurno());
        return cuidadorRepository.save(cuidador);
    }
    public void delete(Long id){
        Cuidador cuidador =  findById(id);
        cuidadorRepository.delete(cuidador);
    }
}

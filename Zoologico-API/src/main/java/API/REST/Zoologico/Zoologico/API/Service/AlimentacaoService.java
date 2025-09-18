package API.REST.Zoologico.Zoologico.API.Service;

import API.REST.Zoologico.Zoologico.API.Model.Alimentacao;
import API.REST.Zoologico.Zoologico.API.Model.Animal;
import API.REST.Zoologico.Zoologico.API.Repository.AlimentacaoRepository;
import API.REST.Zoologico.Zoologico.API.Repository.AnimalRepository;

import java.util.List;

public class AlimentacaoService {
    private AlimentacaoRepository alimentacaoRepository;


    public AlimentacaoService(AlimentacaoRepository alimentacaoRepository) {
        this.alimentacaoRepository = alimentacaoRepository;
    }

    public List<Alimentacao> findAll(){
        return alimentacaoRepository.findAll();
    }

    public List<Alimentacao> createAll(List<Alimentacao> alimentacaos){
        return alimentacaoRepository.saveAll(alimentacaos);
    }

    public Alimentacao create(Alimentacao alimentacao){
        return alimentacaoRepository.save(alimentacao);
    }

    public Alimentacao findById(Long id){
        return alimentacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal não encontrado!"));
    }

    public Alimentacao update(Long id, Alimentacao alimentacaoAtualizado){
        Alimentacao alimentacao = findById(id);

        alimentacao.setComida(alimentacaoAtualizado.getComida());
        alimentacao.setQuantidade(alimentacaoAtualizado.getQuantidade());
        alimentacao.setAnimal(alimentacaoAtualizado.getAnimal());

        return alimentacaoRepository.save(alimentacao);
    }

    public void delete(Long id){
        Alimentacao alimentacao =  findById(id);
        alimentacaoRepository.delete(alimentacao);
    }

}


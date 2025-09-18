package API.REST.Zoologico.Zoologico.API.Repository;

import API.REST.Zoologico.Zoologico.API.Model.Alimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentacaoRepository extends JpaRepository<Alimentacao,Long> {
    List<Alimentacao> findByComidaIgnoreCase(String comida);
    List<Alimentacao> findByAnimalId(Long animalId);
}
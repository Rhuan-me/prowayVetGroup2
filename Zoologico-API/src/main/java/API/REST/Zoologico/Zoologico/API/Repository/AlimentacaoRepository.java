package API.REST.Zoologico.Zoologico.API.Repository;

import API.REST.Zoologico.Zoologico.API.Model.Alimentacao;
import API.REST.Zoologico.Zoologico.API.Model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentacaoRepository extends JpaRepository<Alimentacao,Long> {
    List<Animal> findByEspecieIgnoreCase(String especie);

    @Query("SELECT a FROM Animal a WHERE a.idade BETWEEN :idadeMin AND :idadeMax")
    List<Animal> findByIdade(int idadeMin, int idadeMax);

    List<Animal> findByIdadeBetween(int idadeMin, int idadeMax);

    List<Alimentacao> findByEspecialidadeIgnoreCase(String especialidade);

    List<Alimentacao> findByTurnoIgnoreCase(String turno);
}

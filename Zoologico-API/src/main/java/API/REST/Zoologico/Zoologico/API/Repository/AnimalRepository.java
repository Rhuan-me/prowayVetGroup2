package API.REST.Zoologico.Zoologico.API.Repository;

import API.REST.Zoologico.Zoologico.API.Model.Animal;
import API.REST.Zoologico.Zoologico.API.Model.Habitat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByEspecieIgnoreCase(String especie);

    @Query("SELECT a FROM Animal a WHERE a.idade BETWEEN :idadeMin AND :idadeMax")
    List<Animal> findByIdade(int idadeMin, int idadeMax);

    long countByHabitat(Habitat habitat);
}

package API.REST.Zoologico.Zoologico.API.Repository;

import API.REST.Zoologico.Zoologico.API.Model.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuidadorRepository extends JpaRepository<Cuidador, Long> {
    List<Cuidador> findByTurnoIgnoreCase(String turno);

    List<Cuidador> findByEspecialidadeIgnoreCase(String especialidade);
}
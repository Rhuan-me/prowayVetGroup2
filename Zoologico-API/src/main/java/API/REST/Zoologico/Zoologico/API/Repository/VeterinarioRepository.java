package API.REST.Zoologico.Zoologico.API.Repository;

import API.REST.Zoologico.Zoologico.API.Model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
}

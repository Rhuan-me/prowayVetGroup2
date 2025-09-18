package API.REST.Zoologico.Zoologico.API.Repository;

import API.REST.Zoologico.Zoologico.API.Model.Alimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentacaoRepository extends JpaRepository<Alimentacao,Long> {
}
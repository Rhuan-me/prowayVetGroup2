package API.REST.Zoologico.Zoologico.API.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String especie;

    private int idade;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alimentacao> alimentacao;

    @ManyToMany
    @JoinTable(
            name = "animal_cuidador",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "cuidador_id")
    )
    private Set<Cuidador> cuidadores = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "habitat_id", nullable = false)
    private Habitat habitat;
}

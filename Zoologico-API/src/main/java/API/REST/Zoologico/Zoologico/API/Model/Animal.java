package API.REST.Zoologico.Zoologico.API.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore // Evita recursão infinita ao serializar para JSON
    private List<Alimentacao> alimentacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "animal_cuidador",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "cuidador_id")
    )
    private Set<Cuidador> cuidadores = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "habitat_id", nullable = false)
    private Habitat habitat;
}
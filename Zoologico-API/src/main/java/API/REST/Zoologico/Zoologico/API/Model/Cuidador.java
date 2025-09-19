package API.REST.Zoologico.Zoologico.API.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuidador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String especialidade; // ex: mamíferos, répteis, aves
    private String turno; // manhã, tarde, noite

    @ManyToMany(mappedBy = "cuidadores")
    @JsonIgnore // Adicionado para quebrar a recursão
    private Set<Animal> animais = new HashSet<>();
}
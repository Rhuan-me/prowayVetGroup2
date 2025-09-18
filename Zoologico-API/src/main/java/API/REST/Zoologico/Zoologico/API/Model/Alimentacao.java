package API.REST.Zoologico.Zoologico.API.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comida;
    private double quantidade;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}

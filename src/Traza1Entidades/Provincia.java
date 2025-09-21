package Traza1Entidades;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Provincia {
    private Long id;
    private String nombre;
    private Pais pais;
}

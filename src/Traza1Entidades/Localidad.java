package Traza1Entidades;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Localidad {
    private Long id;
    private String nombre;
    private Provincia provincia;
}

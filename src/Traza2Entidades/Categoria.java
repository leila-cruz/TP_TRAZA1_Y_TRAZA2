package Traza2Entidades;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString

public class Categoria {
    private Long id;
    private String denominacion;
    private boolean esInsumo;
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();
}
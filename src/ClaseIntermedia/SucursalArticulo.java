package ClaseIntermedia;

import Traza1Entidades.Sucursal;
import Traza2Entidades.Articulo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class SucursalArticulo {
    private Long id;
    private Sucursal sucursal;
    private Articulo articulo;
    private Integer stock; // opcional: stock específico por sucursal
}


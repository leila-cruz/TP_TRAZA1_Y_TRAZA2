package Traza1Entidades;


import ClaseIntermedia.SucursalArticulo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString

public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean es_Casa_Matriz;
    private Domicilio domicilio;
    private Empresa empresa;

    @Builder.Default
    private Set<SucursalArticulo> articulos = new HashSet<>();

    public void agregarArticulo(SucursalArticulo sucursalArticulo) {
        articulos.add(sucursalArticulo);
    }
}
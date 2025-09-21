package Traza1Entidades;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Domicilio {
    private Long id;
    private String calle;
    private Integer numero;
    private Integer cp;
    private Localidad localidad;

}
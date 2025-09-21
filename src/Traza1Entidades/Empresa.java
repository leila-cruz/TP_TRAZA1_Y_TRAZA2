package Traza1Entidades;

import lombok.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Empresa {
    private Long id;
    private String nombre;
    private String razonSocial;
    private Integer cuit;
    private String logo;

}

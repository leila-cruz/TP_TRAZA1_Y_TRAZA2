package Traza2Entidades;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString

public class UnidadMedida {
    private Long id;
    private String denominacion;
}

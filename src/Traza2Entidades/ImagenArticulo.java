package Traza2Entidades;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString

public class ImagenArticulo {
    private Long id;
    private String name;
    private String url;
}


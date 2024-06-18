package com.emprendetech.market.service.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IventarioEmprendimientoRespDto {
    private Integer idproducto;
    private String nombre;
    private String sku;
    private String descripcion;
    private Integer idcategoria;
    private String nombreUnidad;
    private Integer idmedida;
    private Double precio;
    private Integer CantidadRestante;
}

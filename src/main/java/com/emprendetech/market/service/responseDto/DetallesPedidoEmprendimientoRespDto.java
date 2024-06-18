package com.emprendetech.market.service.responseDto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetallesPedidoEmprendimientoRespDto {
	
    private Integer idventa;
    private Timestamp fechacreacion;
    private Integer cantidad;
    private String nombreUnidad;
    private String idmedida;
    private Double precio;
    private Integer idproducto;
    private String sku;
    private String nombreProducto;
    private String descripcion;
    private Integer idcategoria;
    private Double subtotal;    
}

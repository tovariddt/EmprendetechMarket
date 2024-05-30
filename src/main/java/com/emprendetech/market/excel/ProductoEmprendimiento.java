package com.emprendetech.market.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoEmprendimiento {
    private Integer idEmprendimiento;
    private String nombreEmprendimiento;
    private Integer idProducto;
    private String sku;
    private String nombreProducto;
    private Integer idCategoria;
    private String descripcion;
    private Integer cantidadDisponible;

    // Getters y Setters
}

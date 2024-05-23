package com.emprendetech.market.service.responseDto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter 
@Setter 
@NoArgsConstructor 
public class ProductosRespDto {


	private String sku;

	private String nombre;

	private String descripcion;

	private Integer cantidad_disponible;

	private String imagen;

	private Integer idemprendimiento;

	private Integer idcategoria;

}

package com.emprendetech.market.service.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoDto {

	public String sku;
	public String nombre;
	public String descripcion;
	public Integer cantidad_disponible;
	public String imagen;
	public Integer idemprendimiento;
	public Integer idcategoria;
	public Integer creadoridusuario;

}

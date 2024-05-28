package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class PrecioDto {
	

	private Integer idproductounidad;

	private Float precio;

	private Integer creadoridusuario;

	private Timestamp fechacreacion;


	private Timestamp fechamodificacion;

	
	
}

package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class ProductosunidadDto {
	private Integer idproducto;
	
	private Integer idmedida;
	
	private Integer creadoridusuario;
	
	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;


}

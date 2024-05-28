package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class DetallespedidoDto {

	private Integer idventa;
	
	private Integer idproductounidad;
	
	private Integer cantidad;
	
	private Float subtotal;
	
	private Integer creadoridusuario;
	
	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;
}

package com.emprendetech.market.service.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class AltaDetallesVentaDto {

	private Integer idproductounidad;
	
	private String nombre;
	
	private Float precio;
	
	
	private Integer cantidad;
	
	private Float subtotal;
	
}

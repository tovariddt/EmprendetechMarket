package com.emprendetech.market.service.requestDto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 

public class AltaVentasDto {
	
	private Integer idventa;
	
	private Integer idmetodospago;
	
    private Date fechaventa;
    
	private String estatus;

	private String tipo;
	
    private Float total;
    
	private Integer creadoridusuario;
	

	
	
}

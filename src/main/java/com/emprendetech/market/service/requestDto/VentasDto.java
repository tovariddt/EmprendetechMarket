package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 

public class VentasDto {
	
	
	
	private Integer idmetodospago;
	
    private Date fechaventa;
    
	private String estatus;

	private String tipo;
	
    private Float total;
    
	private Integer creadoridusuario;
	
	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;


	
	
}

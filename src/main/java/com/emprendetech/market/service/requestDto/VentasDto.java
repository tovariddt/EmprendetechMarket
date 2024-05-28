package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 

public class VentasDto {
	
	
	private Integer idpedido;
	
	private Integer idmetodospago;
	
    private Date fechaventa;
	
    private Float total;

    private Date fecha_pedido;
    
	private Integer creadoridusuario;
	
	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;


	
	
}

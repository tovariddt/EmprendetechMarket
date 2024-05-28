package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter 
@Setter 
@NoArgsConstructor 
public class PedidosDto {
	
	private Integer idcliente;
	
    private Date fecha_pedido;
	
	private String estado;
	
	private Integer creadoridusuario;
	
	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;

}

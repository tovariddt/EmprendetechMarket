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
	
	private Integer idventa;
	
    private Date fechapedido;
	
	private String estatus;
	
	private Integer creadoridusuario;
	
	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;

}

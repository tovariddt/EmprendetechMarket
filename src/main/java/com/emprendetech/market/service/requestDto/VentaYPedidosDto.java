package com.emprendetech.market.service.requestDto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter 
@Setter 
@NoArgsConstructor 
public class VentaYPedidosDto {

	
	private Integer idmetodospago;
	
    private Date fechaventa;
    
	private String estatusventa;

	private String tipo;
	
    private Float total;
    
	private Integer creadoridusuario;
	
	private Integer idcliente;
	
	private Integer idventa;
	
    private Date fechapedido;
	
	private String estatuspedido;
	
	private String nombre;

	private String apellido_materno;

	private String apellido_paterno;

	private String direccion;

	private String referencia;

	private String telefono;
	
	private Integer idcaja;


	
}

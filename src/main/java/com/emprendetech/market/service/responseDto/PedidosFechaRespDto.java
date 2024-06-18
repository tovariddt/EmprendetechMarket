package com.emprendetech.market.service.responseDto;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PedidosFechaRespDto {
	private Integer idventa;
	private Timestamp fechapedido;
	private String ventas_estatus;
	private String  tipo;
	private Double total;
	private String pedidos_estatus;
	private String nombre;
	private String apellido_materno;
	private String apellido_paterno;
	private String direccion;
	private String referencia;
	private String telefono;
	private Integer creadoridusuario;
}

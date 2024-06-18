package com.emprendetech.market.service.responseDto;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VentasFechaRespDto {

	private Integer idventa;
	private Timestamp fechaventa;
	private String estatus;
	private String  tipo;
	private Double total;
	private Integer creadoridusuario;
	
}

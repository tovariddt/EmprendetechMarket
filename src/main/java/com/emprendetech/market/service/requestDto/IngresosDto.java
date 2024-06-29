package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngresosDto {

	

    private Float ingreso;


	private Integer idcaja;
	

	private Integer idventa;
	

	private Integer creadoridusuario;
	

	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;
}

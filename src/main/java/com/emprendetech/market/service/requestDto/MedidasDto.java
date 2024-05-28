package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class MedidasDto {

	private String nombre;
	

	private String abreviatura;
	

	private Integer creadoridusuario;
	

	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;
}

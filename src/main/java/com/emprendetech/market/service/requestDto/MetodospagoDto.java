package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter 
@Setter 
@NoArgsConstructor 
public class MetodospagoDto {
	private String nombre;

	private Integer creadoridusuario;

	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;

	private Integer estatus;
}

package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientesDto {

	private String nombre;

	private String apellido_materno;

	private String apellido_paterno;

	private String direccion;

	private String referencia;

	private String telefono;

	private Integer creadoridusuario;

	private Timestamp fechacreacion;

	private Timestamp fechamodificacion;

}

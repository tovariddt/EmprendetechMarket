package com.emprendetech.market.service.requestDto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistroDto implements Serializable {

	private String nombre;
	private String apellido_materno;
	private String apellido_paterno;
	private String direccion;
	private String referencia;
	private String telefono;
	private String correo;
	private String contrasena;
	private Integer idrol;
	private Integer idperfil;
	private Integer creadoridusuario;
	private Integer id_codigo_postal;
	private String nombre_empren;
	private String descripcion;
	private String industria;

}
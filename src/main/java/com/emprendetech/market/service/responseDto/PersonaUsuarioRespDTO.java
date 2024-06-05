package com.emprendetech.market.service.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonaUsuarioRespDTO {
	public String nombreusuario;
	public String correo;
	public Integer idperfil;
	public String nombre;
	public String apellido_paterno;
	public String apellido_materno;
	public String direccion;
	public String telefono;
	public Integer id_codigo_postal;

}

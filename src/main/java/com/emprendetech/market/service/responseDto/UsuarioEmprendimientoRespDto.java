package com.emprendetech.market.service.responseDto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioEmprendimientoRespDto {

	List<PersonaUsuarioRespDTO> personausuario = new ArrayList<PersonaUsuarioRespDTO>();
	List<EmprendimientosRespDTO> emprendimiento = new ArrayList<EmprendimientosRespDTO>();

}

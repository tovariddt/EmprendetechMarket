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

	List<PersonaUsuarioRespDto> personausuario = new ArrayList<PersonaUsuarioRespDto>();
	List<EmprendimientosRespDto> emprendimiento = new ArrayList<EmprendimientosRespDto>();

}

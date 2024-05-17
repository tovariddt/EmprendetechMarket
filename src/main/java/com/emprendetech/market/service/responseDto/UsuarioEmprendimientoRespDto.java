package com.emprendetech.market.service.responseDto;

import java.util.ArrayList;
import java.util.List;

public class UsuarioEmprendimientoRespDto {


	List<PersonaUsuarioRespDTO> personausuario = new ArrayList<PersonaUsuarioRespDTO>();
	List<EmprendimientosRespDTO> emprendimiento = new ArrayList<EmprendimientosRespDTO>();
	public List<PersonaUsuarioRespDTO> getPersonausuario() {
		return personausuario;
	}
	public void setPersonausuario(List<PersonaUsuarioRespDTO> personausuario) {
		this.personausuario = personausuario;
	}
	public List<EmprendimientosRespDTO> getEmprendimiento() {
		return emprendimiento;
	}
	public void setEmprendimiento(List<EmprendimientosRespDTO> emprendimiento) {
		this.emprendimiento = emprendimiento;
	}

}

package com.emprendetech.market.service.responseDto;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class EmprendimientosRespDTO {

	public String nombre;
	public String descripcion;
    public String industria;
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIndustria() {
		return industria;
	}
	public void setIndustria(String industria) {
		this.industria = industria;
	}
    
    
	
}

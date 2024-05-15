package com.emprendetech.market.service.responseDto;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class PostalRespDto {

	public Integer id_codigo_postal;
	public String clave;
	public String nombre_estado;
	public String nombre_municipio;
	public String nombre_localidad;
	
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre_estado() {
		return nombre_estado;
	}
	public void setNombre_estado(String nombre_estado) {
		this.nombre_estado = nombre_estado;
	}
	public String getNombre_municipio() {
		return nombre_municipio;
	}
	public void setNombre_municipio(String nombre_municipio) {
		this.nombre_municipio = nombre_municipio;
	}
	public String getNombre_localidad() {
		return nombre_localidad;
	}
	public void setNombre_localidad(String nombre_localidad) {
		this.nombre_localidad = nombre_localidad;
	}
	public Integer getId_codigo_postal() {
		return id_codigo_postal;
	}
	public void setId_codigo_postal(Integer id_codigo_postal) {
		this.id_codigo_postal = id_codigo_postal;
	}
	
	

}

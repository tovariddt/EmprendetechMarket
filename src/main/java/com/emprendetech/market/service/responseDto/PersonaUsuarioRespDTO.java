package com.emprendetech.market.service.responseDto;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class PersonaUsuarioRespDTO {	
	public String nombreusuario;
	public String correo;
	public Integer idrol;
 	public Integer idperfil;
 	public String nombre;
 	public  String apellido_paterno;
 	public  String apellido_materno;
 	public  String direccion;
 	public  String telefono;
 	public  Integer id_codigo_postal;

 	
	public String getNombreusuario() {
		return nombreusuario;
	}
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Integer getIdrol() {
		return idrol;
	}
	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}
	public Integer getIdperfil() {
		return idperfil;
	}
	public void setIdperfil(Integer idperfil) {
		this.idperfil = idperfil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getId_codigo_postal() {
		return id_codigo_postal;
	}
	public void setId_codigo_postal(Integer id_codigo_postal) {
		this.id_codigo_postal = id_codigo_postal;
	}

 	
}

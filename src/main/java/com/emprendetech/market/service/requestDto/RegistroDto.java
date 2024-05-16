package com.emprendetech.market.service.requestDto;


import java.io.Serializable;
import java.sql.Timestamp;

public class RegistroDto implements Serializable{
	
	private String nombre;
	private String apellido_materno;
	private String apellido_paterno;
	private String direccion;
	private String referencia;
	private String telefono;
	private String correo;
	private String contrasena;
	private Integer idrol ;
	private Integer idperfil ;
	private Integer creadoridusuario;
	private Integer id_codigo_postal;
	private String nombre_empren;
	private String descripcion;
	private String industria;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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
	public Integer getCreadoridusuario() {
		return creadoridusuario;
	}
	public void setCreadoridusuario(Integer creadoridusuario) {
		this.creadoridusuario = creadoridusuario;
	}
	public Integer getId_codigo_postal() {
		return id_codigo_postal;
	}
	public void setId_codigo_postal(Integer id_codigo_postal) {
		this.id_codigo_postal = id_codigo_postal;
	}
	public String getNombre_empren() {
		return nombre_empren;
	}
	public void setNombre_empren(String nombre_empren) {
		this.nombre_empren = nombre_empren;
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
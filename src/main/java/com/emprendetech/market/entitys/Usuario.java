package com.emprendetech.market.entitys;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="usuario")
public class Usuario {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO) 
@Column(name = "idusuario")
private Integer idusuario;

@Column(name = "nombreusuario")
private String nombreusuaruio;

@Column(name = "correo")
private String correo;

@Column(name = "contrasena")
private String contrasena;

@Column(name = "idrol")
private Integer idrol;

@Column(name = "idperfil")
private Integer idperfil;

@Column(name = "idpersona")
private Integer idpersona;

@Column(name="fechacreacion")
private Timestamp fechacreacion;

@Column(name="fechamodificacion")
private Timestamp fechamodificacion;

@Column(name = "creadoridusuario")
private Integer creadoridusuario;

public Integer getIdusuario() {
	return idusuario;
}

public void setIdusuario(Integer idusuario) {
	this.idusuario = idusuario;
}

public String getNombreusuarui() {
	return nombreusuaruio;
}

public void setNombreusuarui(String nombreusuaruio) {
	this.nombreusuaruio = nombreusuaruio;
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

public Integer getIdpersona() {
	return idpersona;
}

public void setIdpersona(Integer idpersona) {
	this.idpersona = idpersona;
}

public Timestamp getFechacreacion() {
	return fechacreacion;
}

public void setFechacreacion(Timestamp fechacreacion) {
	this.fechacreacion = fechacreacion;
}

public Timestamp getFechamodificacion() {
	return fechamodificacion;
}

public void setFechamodificacion(Timestamp fechamodificacion) {
	this.fechamodificacion = fechamodificacion;
}

public Integer getCreadoridusuario() {
	return creadoridusuario;
}

public void setCreadoridusuario(Integer creadoridusuario) {
	this.creadoridusuario = creadoridusuario;
}

}

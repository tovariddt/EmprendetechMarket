package com.emprendetech.market.entitys;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter 
@Setter 
@NoArgsConstructor 
@Entity
@Table(name="usuario")
public class Usuario {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO) 
@Column(name = "idusuario")
private Integer idusuario;

@Column(name = "nombreusuario")
private String nombreusuario;

@Column(name = "correo")
private String correo;

@Column(name = "contraseña")
private String contraseña;

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



}

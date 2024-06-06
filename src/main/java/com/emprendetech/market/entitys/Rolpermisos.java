package com.emprendetech.market.entitys;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rolpermisos")
public class Rolpermisos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idrolpermisos")
	private Integer idrolpermisos;

	@Column(name = "idrol")
	private Integer idrol;

	@Column(name = "idpermisos")
	private Integer idpermisos;

	@Column(name = "creadoridusuario")
	private Integer creadoridusuario;

	@Column(name = "fechacreacion")
	private Timestamp fechacreacion;

	@Column(name = "fechamodificacion")
	private Timestamp fechamodificacion;

}
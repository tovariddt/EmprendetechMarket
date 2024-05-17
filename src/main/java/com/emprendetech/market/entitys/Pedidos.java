package com.emprendetech.market.entitys;

import java.sql.Timestamp;

import java.util.Date;

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
@Table(name = "pedidos")
public class Pedidos {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idpedido")
	private Integer idpedido;
	
	@Column(name = "idcliente")
	private Integer idcliente;
	
	@Column(name = "fecha_pedido")
    private Date fecha_pedido;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "creadoridusuario")
	private Integer creadoridusuario;
	
	@Column(name="fechacreacion")
	private Timestamp fechacreacion;

	@Column(name="fechamodificacion")
	private Timestamp fechamodificacion;


}

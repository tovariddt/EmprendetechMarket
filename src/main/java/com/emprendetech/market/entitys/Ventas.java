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
@Table(name = "ventas")
public class Ventas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idventa")
	private Integer idventa;
	
	@Column(name = "idpedido")
	private Integer idpedido;
	
	@Column(name = "idmetodospago")
	private Integer idmetodospago;
	
	@Column(name = "fechaventa")
    private Date fechaventa;
	
    @Column(name = "total")
    private Float total;
    
	@Column(name = "creadoridusuario")
	private Integer creadoridusuario;
	
	@Column(name="fechacreacion")
	private Timestamp fechacreacion;

	@Column(name="fechamodificacion")
	private Timestamp fechamodificacion;


	
	
}

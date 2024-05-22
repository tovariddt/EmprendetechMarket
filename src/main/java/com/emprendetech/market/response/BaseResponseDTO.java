package com.emprendetech.market.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author albarranoi
 *
 */
public class BaseResponseDTO implements Serializable {

	private static final long serialVersionUID = 8753323195665770912L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String codigo;
	private String mensaje;

	public BaseResponseDTO(String codigo, String mensaje) {
		super();
		this.timestamp = LocalDateTime.now();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}

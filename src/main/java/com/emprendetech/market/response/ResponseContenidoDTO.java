package com.emprendetech.market.response;

import org.springframework.http.ResponseEntity;

/**
 * @author jaguilarh
 *
 */
public class ResponseContenidoDTO<T> extends BaseResponseDTO {

	private static final long serialVersionUID = 2225300682391223642L;

	public ResponseContenidoDTO(String codigo, String mensaje) {
		super(codigo, mensaje);
	}

	private T contenido;

	public T getContenido() {
		return contenido;
	}

	public void setContenido(T contenido) {
		this.contenido = contenido;
	}

}

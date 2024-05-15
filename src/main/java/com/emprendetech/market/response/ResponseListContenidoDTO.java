package com.emprendetech.market.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author albarranoi
 *
 */
public class ResponseListContenidoDTO<T> extends BaseResponseDTO {

	private static final long serialVersionUID = -6017362001487067921L;

	public ResponseListContenidoDTO(String codigo, String mensaje) {
		super(codigo, mensaje);
	}
	
	private List<T> contenido = new ArrayList<T>();

	public List<T> getContenido() {
		return contenido;
	}

	public void setContenido(List<T> contenido) {
		this.contenido = contenido;
	}
	
}

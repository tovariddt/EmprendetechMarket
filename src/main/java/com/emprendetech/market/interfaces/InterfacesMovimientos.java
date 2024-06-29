package com.emprendetech.market.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.service.requestDto.DetallespedidoDto;
import com.emprendetech.market.service.requestDto.VentaYPedidosDto;
import com.emprendetech.market.service.requestDto.VentasDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface InterfacesMovimientos {

	
	
	ResponseEntity<?> postUnaVentaPedido(VentaYPedidosDto ventaYPedidosDto);


	ResponseEntity<?> postDetalleVenta(DetallespedidoDto detallespedidoDto);


	ResponseEntity<?> getVentaDetalleventa(DetallespedidoDto detallespedidoDto);
	
	
}

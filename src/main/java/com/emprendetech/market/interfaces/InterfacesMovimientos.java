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

	
	
	@PostMapping("/ventapedido")
	@Operation(summary = "Se realiza un registro de una venta")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de una venta")
	ResponseEntity<?> postUnaVentaPedido(VentaYPedidosDto ventaYPedidosDto);

	@PostMapping("/detallepedido")
	@Operation(summary = "Se realiza un registro de un detallepedido")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de un detallepedido")
	ResponseEntity<?> postDetalleVenta(DetallespedidoDto detallespedidoDto);

	@GetMapping("/ventadetalle")
	@Operation(summary = "Se realiza una consulta a venta y  detallepedido")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una consulta a venta y  detallepedido")
	ResponseEntity<?> getVentaDetalleventa(DetallespedidoDto detallespedidoDto);
	
	
}

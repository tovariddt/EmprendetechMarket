package com.emprendetech.market.servicios.front.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emprendetech.market.controllers.AltaMovimientosController;
import com.emprendetech.market.dao.PlataformaMovimientoDao;
import com.emprendetech.market.interfaces.InterfacesMovimientos;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.AltaDetallesVentaDto;
import com.emprendetech.market.service.requestDto.AltaVentasDto;
import com.emprendetech.market.service.requestDto.DetallespedidoDto;
import com.emprendetech.market.service.requestDto.VentaYPedidosDto;
import com.emprendetech.market.service.requestDto.VentasDto;
import com.emprendetech.market.service.responseDto.VentaDetallesPedido;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ResponseBody
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)

@RestController
@RequestMapping(path = "/api/movimientos")
public class PlataformaVentaPedidoImpl implements InterfacesMovimientos {
	private static final Log LOG = LogFactory.getLog(PlataformaVentaPedidoImpl.class);

	@Autowired
	private AltaMovimientosController UnaVentaoPedido , UnaDetallespedido;
	@Autowired
	private PlataformaMovimientoDao CabezaVenta , CuerpoVenta;

	@Override
	@PostMapping("/ventapedido")
	@Operation(summary = "Se realiza un registro de una venta")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de una venta")
	public ResponseEntity<?> postUnaVentaPedido(@RequestBody VentaYPedidosDto ventaYPedidosDto) {

		LOG.info("get Ventas - get Ventas() Method");
		ResponseEntity<?> responsealtaVentas = null;

		try {
			String altaVentas = null;

			altaVentas = UnaVentaoPedido.UnaVentaoPedido(ventaYPedidosDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Ventas");
			responseContenido.setContenido(altaVentas);
			responsealtaVentas = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Ventas");
			responsealtaVentas = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaVentas;
	}
	
	@Override
	@PostMapping("/detallepedido")
	@Operation(summary = "Se realiza un registro de un detallepedido")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de un detallepedido")
	public ResponseEntity<?> postDetalleVenta(@RequestBody DetallespedidoDto detallespedidoDto) {
		LOG.info("get Detallespedido - get Detallespedido() Method");
		ResponseEntity<?> responsealtaDetallespedido = null;

		try {

			String altaDetallespedido = null;

			altaDetallespedido = UnaDetallespedido.UnaDetallespedido(detallespedidoDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Detallespedido");
			responseContenido.setContenido(altaDetallespedido);
			responsealtaDetallespedido = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Detallespedido");
			responsealtaDetallespedido = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaDetallespedido;
	}
	
	@Override
	@GetMapping("/ventadetalle")
	@Operation(summary = "Se realiza una consulta a venta y  detallepedido")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una consulta a venta y  detallepedido")
	public ResponseEntity<?> getVentaDetalleventa(@RequestBody DetallespedidoDto detallespedidoDto) {
		LOG.info("get VentaDetalleventa -  get VentaDetalleventa() Method");

		ResponseEntity<?> responseConsulta = null;
		try {
		Integer idventa = detallespedidoDto.getIdventa();
	
			List<AltaVentasDto> resultVenta = new ArrayList<AltaVentasDto>();
			resultVenta = CabezaVenta.getMovimientoVenta(idventa);

			List<AltaDetallesVentaDto> resultDetalles = new ArrayList<AltaDetallesVentaDto>();
			resultDetalles = CuerpoVenta.getDelleventa(idventa);

			VentaDetallesPedido ventaDetallesPedido = new VentaDetallesPedido();
			ventaDetallesPedido.setEncabezado(resultVenta);
			ventaDetallesPedido.setDetalles(resultDetalles);

			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Consulta VentaDetalleventa");
			responseContenido.setContenido(ventaDetallesPedido);
			responseConsulta = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Consulta VentaDetalleventa");
			responseConsulta = new ResponseEntity<>(responseContenido, HttpStatus.OK);
		}
		return responseConsulta;
	}

}

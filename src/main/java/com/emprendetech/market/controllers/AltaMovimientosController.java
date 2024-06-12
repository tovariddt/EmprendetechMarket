package com.emprendetech.market.controllers;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.dao.PlataformaMovimientoDao;
import com.emprendetech.market.entitys.Clientes;
import com.emprendetech.market.entitys.Detallespedido;
import com.emprendetech.market.entitys.Pedidos;
import com.emprendetech.market.entitys.Productosunidad;
import com.emprendetech.market.entitys.Ventas;
import com.emprendetech.market.repositorys.ClientesRepository;
import com.emprendetech.market.repositorys.DetallespedidoRepository;
import com.emprendetech.market.repositorys.PedidosRepository;
import com.emprendetech.market.repositorys.ProductosunidadRepository;
import com.emprendetech.market.repositorys.VentasRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.DetallespedidoDto;
import com.emprendetech.market.service.requestDto.VentaYPedidosDto;
import com.emprendetech.market.utils.Constantes;
import com.emprendetech.market.utils.Utils;

import lombok.Data;

@Data
@Controller
public class AltaMovimientosController {
	private static final Log LOG = LogFactory.getLog(AltaMovimientosController.class);

	@Autowired
	private PlataformaMovimientoDao ClienteExistente, TotalVentas, PrecioProductoUnidad, CantidadProductoUnidad;
	@Autowired
	private VentasRepository ventasRepository;
	@Autowired
	private ProductosunidadRepository productosunidadRepository;
	@Autowired
	private DetallespedidoRepository detallespedidoRepository;
	@Autowired
	private ClientesRepository clientesRepository;
	@Autowired
	private PedidosRepository pedidosRepository;
	
	public String UnaVentaoPedido(@RequestBody VentaYPedidosDto ventaYPedidosDto) throws Exception {
		LOG.info("createAlta UnaVenta- createAlta UnaVenta() Method");

		String response = null;
		String Venta=null;
		String Pedido=null;
		
		try {

			Ventas VentasInsert = new Ventas();
			Date dateVentas = new Date();

			VentasInsert.setIdmetodospago(ventaYPedidosDto.getIdmetodospago());
			VentasInsert.setFechaventa(dateVentas);
			VentasInsert.setCreadoridusuario(ventaYPedidosDto.getCreadoridusuario());
			VentasInsert.setTipo(ventaYPedidosDto.getTipo());
			VentasInsert.setEstatus(ventaYPedidosDto.getEstatusventa());

			Utils util = new Utils();
			VentasInsert.setFechacreacion(util.currentDate());
			VentasInsert.setFechamodificacion(util.currentDate());

			LOG.info("Alta UnaVenta - Alta UnaVenta() Method " + VentasInsert.toString());

			VentasInsert = ventasRepository.save(VentasInsert);
			
			Venta="Su id de venta es ="+VentasInsert.getIdventa();
	

			if ("Pedido".equals(ventaYPedidosDto.getTipo())) {

				Integer idcliente = ClienteExistente.getCliente(ventaYPedidosDto.getIdcliente());
				
				if (idcliente == null) {

					Clientes ClientesInsert = new Clientes();
					ClientesInsert.setNombre(ventaYPedidosDto.getNombre());
					ClientesInsert.setApellido_materno(ventaYPedidosDto.getApellido_materno());
					ClientesInsert.setApellido_paterno(ventaYPedidosDto.getApellido_paterno());
					ClientesInsert.setDireccion(ventaYPedidosDto.getDireccion());
					ClientesInsert.setCreadoridusuario(ventaYPedidosDto.getCreadoridusuario());
					ClientesInsert.setReferencia(ventaYPedidosDto.getReferencia());
					ClientesInsert.setTelefono(ventaYPedidosDto.getTelefono());
					ClientesInsert.setFechacreacion(util.currentDate());
					ClientesInsert.setFechamodificacion(util.currentDate());
					LOG.info("Alta Clientes - Alta Clientes() Method " + ClientesInsert.toString());

					ClientesInsert = clientesRepository.save(ClientesInsert);
					
					Pedidos PedidosInsert = new Pedidos();
					Date datePedido = new Date();
					PedidosInsert.setIdventa(VentasInsert.getIdventa());
					PedidosInsert.setIdcliente(ClientesInsert.getIdcliente());
					PedidosInsert.setFechapedido(datePedido);
					PedidosInsert.setEstatus(ventaYPedidosDto.getEstatuspedido());
					PedidosInsert.setCreadoridusuario(ventaYPedidosDto.getCreadoridusuario());
					PedidosInsert.setFechacreacion(util.currentDate());
					PedidosInsert.setFechamodificacion(util.currentDate());

					LOG.info("Alta Pedidos - Alta Pedidos() Method " + PedidosInsert.toString());

					PedidosInsert = pedidosRepository.save(PedidosInsert);		
					
					Pedido="Su id de pedido es ="+PedidosInsert.getIdpedido()+" Su id de cliente es ="+ClientesInsert.getIdcliente();

				}else {
					
				Pedidos PedidosInsert = new Pedidos();
				Date datePedido = new Date();
				PedidosInsert.setIdventa(VentasInsert.getIdventa());
				PedidosInsert.setIdcliente(idcliente);
				PedidosInsert.setFechapedido(datePedido);
				PedidosInsert.setEstatus(ventaYPedidosDto.getEstatuspedido());
				PedidosInsert.setCreadoridusuario(ventaYPedidosDto.getCreadoridusuario());
				PedidosInsert.setFechacreacion(util.currentDate());
				PedidosInsert.setFechamodificacion(util.currentDate());
				LOG.info("Alta Pedidos - Alta Pedidos() Method " + PedidosInsert.toString());
				PedidosInsert = pedidosRepository.save(PedidosInsert);
				
				Pedido="Su id de pedido es ="+PedidosInsert.getIdpedido()+" Su id de cliente es "+idcliente;

				}
				
			}

			response =Venta + Pedido;

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error",
					ventaYPedidosDto.toString());
		}
		return response;
	}

	public String UnaDetallespedido(@RequestBody DetallespedidoDto detallespedidoDto) throws Exception {
		LOG.info("createAlta Detallespedido- createAlta Detallespedido() Method");

		String response = null;
		try {
			Integer cantidadproductounidad = CantidadProductoUnidad
					.getCantidadProductoUnidad(detallespedidoDto.getIdproductounidad());

			if (cantidadproductounidad >= detallespedidoDto.getCantidad()) {

				Detallespedido DetallespedidoInsert = new Detallespedido();
				DetallespedidoInsert.setIdventa(detallespedidoDto.getIdventa());
				DetallespedidoInsert.setIdproductounidad(detallespedidoDto.getIdproductounidad());
				DetallespedidoInsert.setCantidad(detallespedidoDto.getCantidad());
				DetallespedidoInsert.setCreadoridusuario(detallespedidoDto.getCreadoridusuario());

				float precioproductounidad = PrecioProductoUnidad
						.getPrecioProductoUnidad(detallespedidoDto.getIdproductounidad());
				Float subtotal = precioproductounidad * detallespedidoDto.getCantidad();

				DetallespedidoInsert.setSubtotal(subtotal);

				Utils util = new Utils();
				DetallespedidoInsert.setFechacreacion(util.currentDate());
				DetallespedidoInsert.setFechamodificacion(util.currentDate());

				LOG.info("Alta Detallespedido - Alta Detallespedido() Method " + DetallespedidoInsert.toString());

				DetallespedidoInsert = detallespedidoRepository.save(DetallespedidoInsert);

				response = Constantes.FELICIDADESALTADETALLESPEDIDO + "Venta Nº "
						+ String.valueOf(detallespedidoDto.getIdventa());

				BigDecimal totalventa = TotalVentas.getTotalVentas(detallespedidoDto.getIdventa());
				float total = totalventa.floatValue();
				LOG.info("LLegue a total venta " + total);

				Ventas ventasExistente = ventasRepository.findById(detallespedidoDto.getIdventa()).orElseThrow();
				ventasExistente.setTotal(total);
				ventasExistente = ventasRepository.save(ventasExistente);

				Integer cantidadrestante = (cantidadproductounidad - detallespedidoDto.getCantidad());

				Productosunidad productosunidadExistente = productosunidadRepository
						.findById(detallespedidoDto.getIdproductounidad()).orElseThrow();
				productosunidadExistente.setCantidad(cantidadrestante);
				productosunidadExistente = productosunidadRepository.save(productosunidadExistente);

			} else {
				response = Constantes.CANTIDADINSUFICIENTE;
			}

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error",
					detallespedidoDto.toString());
		}
		return response;
	}

}

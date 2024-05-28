package com.emprendetech.market.controllers;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Pedidos;
import com.emprendetech.market.repositorys.PedidosRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.PedidosDto;
import com.emprendetech.market.utils.Utils;

import lombok.Data;

@Data
@Controller
public class PedidosController {

	private static final Log LOG = LogFactory.getLog(PedidosController.class);

	@Autowired
	private PedidosRepository pedidosRepository;

	public String AltaPedidos(@RequestBody PedidosDto pedidosDto) throws Exception {
		LOG.info("createAlta Pedidos- createAlta Pedidos() Method");
		LOG.debug("createAlta Pedidos:: " + pedidosDto.toString());

		String response = null;
		try {
			Pedidos PedidosInsert = new Pedidos();
			Date datePedido = new Date(); 
			PedidosInsert.setIdcliente(pedidosDto.getIdcliente());
			PedidosInsert.setFecha_pedido(datePedido);
			PedidosInsert.setEstado(pedidosDto.getEstado());
			PedidosInsert.setCreadoridusuario(pedidosDto.getCreadoridusuario());


			Utils util = new Utils();
			PedidosInsert.setFechacreacion(util.currentDate());
			PedidosInsert.setFechamodificacion(util.currentDate());

			LOG.info("Alta Pedidos - Alta Pedidos() Method " + PedidosInsert.toString());

			PedidosInsert = pedidosRepository.save(PedidosInsert);

			response = "Felicidades Su Pedido fue registrado con el ID = " + PedidosInsert.getIdpedido();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", pedidosDto.toString());
		}
		return response;
	}

	public String actualizarPedidos(@RequestBody Pedidos pedidos) throws Exception {
		LOG.info("update Pedidos - update Pedidos() Method");
		LOG.debug("update Pedidos :: " + pedidos.toString());

		String response = null;

		try {
			Pedidos pedidosExistente = pedidosRepository.findById(pedidos.getIdpedido()).orElseThrow();

			Date datePedido = new Date(); 

			pedidosExistente.setIdcliente(pedidos.getIdcliente());
			pedidosExistente.setFecha_pedido(datePedido);
			pedidosExistente.setEstado(pedidos.getEstado());
			pedidosExistente.setCreadoridusuario(pedidos.getCreadoridusuario());
			
			Utils util = new Utils();
			pedidosExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Pedidos - update Pedidos() Method " + pedidosExistente.toString());

			pedidosExistente = pedidosRepository.save(pedidos);

			response = "Su Pedido " + pedidosExistente.getIdpedido() + " ha sido actualizado exitosamente.";
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", pedidos.toString());
		}
		return response;
	}


}

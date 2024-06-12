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
import com.emprendetech.market.utils.Constantes;
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

		String response = null;
		try {
			Pedidos PedidosInsert = new Pedidos();
			Date datePedido = new Date(); 
			PedidosInsert.setIdventa(pedidosDto.getIdventa());
			PedidosInsert.setIdcliente(pedidosDto.getIdcliente());
			PedidosInsert.setFechapedido(datePedido);
			PedidosInsert.setEstatus(pedidosDto.getEstatus());
			PedidosInsert.setCreadoridusuario(pedidosDto.getCreadoridusuario());


			Utils util = new Utils();
			PedidosInsert.setFechacreacion(util.currentDate());
			PedidosInsert.setFechamodificacion(util.currentDate());

			LOG.info("Alta Pedidos - Alta Pedidos() Method " + PedidosInsert.toString());

			PedidosInsert = pedidosRepository.save(PedidosInsert);

			response = Constantes.FELICIDADESALTAPEDIDOS+ PedidosInsert.getIdpedido();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", pedidosDto.toString());
		}
		return response;
	}

	public String actualizarPedidos(@RequestBody Pedidos pedidos) throws Exception {
		LOG.info("update Pedidos - update Pedidos() Method");

		String response = null;

		try {
			Pedidos pedidosExistente = pedidosRepository.findById(pedidos.getIdpedido()).orElseThrow();

		
			pedidosExistente.setIdventa(pedidos.getIdventa());
			pedidosExistente.setIdcliente(pedidos.getIdcliente());
			pedidosExistente.setEstatus(pedidos.getEstatus());
			pedidosExistente.setCreadoridusuario(pedidos.getCreadoridusuario());
			
			Utils util = new Utils();
			pedidosExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Pedidos - update Pedidos() Method " + pedidosExistente.toString());

			pedidosExistente = pedidosRepository.save(pedidos);

			response = Constantes.FELICIDADESACTUALIZARPEDIDOS + pedidosExistente.getIdpedido() ;
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", pedidos.toString());
		}
		return response;
	}


}

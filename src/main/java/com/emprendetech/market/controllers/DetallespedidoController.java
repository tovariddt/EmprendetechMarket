package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Detallespedido;
import com.emprendetech.market.repositorys.DetallespedidoRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.DetallespedidoDto;
import com.emprendetech.market.utils.Utils;

import lombok.Data;
@Data
@Controller
public class DetallespedidoController {
	private static final Log LOG = LogFactory.getLog(DetallespedidoController.class);

	@Autowired
	private DetallespedidoRepository detallespedidoRepository;

	public String AltaDetallespedido(@RequestBody DetallespedidoDto detallespedidoDto) throws Exception {
		LOG.info("createAlta Detallespedido- createAlta Detallespedido() Method");
		LOG.debug("createAlta Detallespedido:: " + detallespedidoDto.toString());

		String response = null;
		try {
			Detallespedido DetallespedidoInsert = new Detallespedido();

			DetallespedidoInsert.setIdventa(detallespedidoDto.getIdventa());
			DetallespedidoInsert.setIdproductounidad(detallespedidoDto.getIdproductounidad());
			DetallespedidoInsert.setCantidad(detallespedidoDto.getCantidad());
			DetallespedidoInsert.setSubtotal(detallespedidoDto.getSubtotal());
			DetallespedidoInsert.setCreadoridusuario(detallespedidoDto.getCreadoridusuario());
	


			Utils util = new Utils();
			DetallespedidoInsert.setFechacreacion(util.currentDate());
			DetallespedidoInsert.setFechamodificacion(util.currentDate());

			LOG.info("Alta Detallespedido - Alta Detallespedido() Method " + DetallespedidoInsert.toString());

			DetallespedidoInsert = detallespedidoRepository.save(DetallespedidoInsert);

			response = "Felicidades su Detalle pedido  fue registrado con el id de venta  = " + DetallespedidoInsert.getIdventa();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", detallespedidoDto.toString());
		}
		return response;
	}

	public String actualizarDetallespedido(@RequestBody Detallespedido detallespedido) throws Exception {
		LOG.info("update Detallespedido - update Detallespedido() Method");
		LOG.debug("update Detallespedido :: " + detallespedido.toString());

		String response = null;

		try {
			Detallespedido detallespedidoExistente = detallespedidoRepository.findById(detallespedido.getIddetallespedido()).orElseThrow();

			detallespedidoExistente.setIdventa(detallespedido.getIdventa());
			detallespedidoExistente.setIdproductounidad(detallespedido.getIdproductounidad());
			detallespedidoExistente.setCantidad(detallespedido.getCantidad());
			detallespedidoExistente.setSubtotal(detallespedido.getSubtotal());
			detallespedidoExistente.setCreadoridusuario(detallespedido.getCreadoridusuario());
			
			Utils util = new Utils();
			detallespedidoExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Detallespedido - update Detallespedido() Method " + detallespedidoExistente.toString());

			detallespedidoExistente = detallespedidoRepository.save(detallespedido);

			response = "El Detalles pedido de la venta " + detallespedido.getIdventa() + " ha sido actualizado exitosamente.";
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", detallespedido.toString());
		}
		return response;
	}
	

}

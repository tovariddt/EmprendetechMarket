package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Clientes;
import com.emprendetech.market.repositorys.ClientesRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.ClientesDto;
import com.emprendetech.market.utils.Utils;

import lombok.Data;
@Data
@Controller
public class ClientesController {

	private static final Log LOG = LogFactory.getLog(ClientesController.class);

	@Autowired
	private ClientesRepository clientesRepository;

	public String AltaClientes(@RequestBody ClientesDto clientesDto) throws Exception {
		LOG.info("createAlta Clientes- createAlta Clientes() Method");
		LOG.debug("createAlta Clientes:: " + clientesDto.toString());

		String response = null;
		try {
			Clientes ClientesInsert = new Clientes();

			ClientesInsert.setNombre(clientesDto.getNombre());
			ClientesInsert.setApellido_materno(clientesDto.getApellido_materno());
			ClientesInsert.setApellido_paterno(clientesDto.getApellido_paterno());
			ClientesInsert.setDireccion(clientesDto.getDireccion());
			ClientesInsert.setCreadoridusuario(clientesDto.getCreadoridusuario());
			ClientesInsert.setReferencia(clientesDto.getReferencia());
			ClientesInsert.setTelefono(clientesDto.getTelefono());

			Utils util = new Utils();
			ClientesInsert.setFechacreacion(util.currentDate());
			ClientesInsert.setFechamodificacion(util.currentDate());

			LOG.info("Alta Clientes - Alta Clientes() Method " + ClientesInsert.toString());

			ClientesInsert = clientesRepository.save(ClientesInsert);

			response = "Felicidades Su Cliente fue registrado como = " + ClientesInsert.getNombre();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", clientesDto.toString());
		}
		return response;
	}

	public String actualizarClientes(@RequestBody Clientes clientes) throws Exception {
		LOG.info("update Clientes - update Clientes() Method");
		LOG.debug("update Clientes :: " + clientes.toString());

		String response = null;

		try {
			Clientes clientesExistente = clientesRepository.findById(clientes.getIdcliente()).orElseThrow();

			clientesExistente.setNombre(clientes.getNombre());
			clientesExistente.setApellido_materno(clientes.getApellido_materno());
			clientesExistente.setApellido_paterno(clientes.getApellido_paterno());
			clientesExistente.setDireccion(clientes.getDireccion());
			clientesExistente.setCreadoridusuario(clientes.getCreadoridusuario());
			clientesExistente.setReferencia(clientes.getReferencia());
			clientesExistente.setTelefono(clientes.getTelefono());

			Utils util = new Utils();
			clientesExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Clientes - update Clientes() Method " + clientesExistente.toString());

			clientesExistente = clientesRepository.save(clientes);

			response = "El Clientes " + clientesExistente.getNombre() + " ha sido actualizado exitosamente.";
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", clientes.toString());
		}
		return response;
	}

}

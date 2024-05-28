package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Rol_permisos;
import com.emprendetech.market.repositorys.Rol_permisosRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.Rol_permisosDto;
import com.emprendetech.market.utils.Utils;

import lombok.Data;

@Data
@Controller
public class Rol_permisosController {

	private static final Log LOG = LogFactory.getLog(Rol_permisosController.class);

	@Autowired
	private Rol_permisosRepository rol_permisosRepository;

	public String AltaRol_permisos(@RequestBody Rol_permisosDto rol_permisosDto) throws Exception {
		LOG.info("createAlta Rol_permisos- createAlta Rol_permisos() Method");
		LOG.debug("createAlta Rol_permisos:: " + rol_permisosDto.toString());

		String response = null;
		try {
			Rol_permisos Rol_permisosInsert = new Rol_permisos();

			Rol_permisosInsert.setIdrol(rol_permisosDto.getIdrol());
			Rol_permisosInsert.setIdpermisos(rol_permisosDto.getIdpermisos());
			Rol_permisosInsert.setCreadoridusuario(rol_permisosDto.getCreadoridusuario());

			Utils util = new Utils();
			Rol_permisosInsert.setFechacreacion(util.currentDate());
			Rol_permisosInsert.setFechamodificacion(util.currentDate());
			
			LOG.info("Alta Rol_permisos - Alta Rol_permisos() Method " + Rol_permisosInsert.toString());

			Rol_permisosInsert = rol_permisosRepository.save(Rol_permisosInsert);

			response = "Felicidades Su Rol_permisos fue registrado con el siguiente ID = " + Rol_permisosInsert.getIdrol_permisos();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", rol_permisosDto.toString());
		}
		return response;
	}
	
	public String actualizarRol_permisos(@RequestBody Rol_permisos rol_permisos) throws Exception {
		LOG.info("update Rol_permisos - update Rol_permisos() Method");
		LOG.debug("update Rol_permisos :: " + rol_permisos.toString());

		String response = null;

		try {
			Rol_permisos rol_permisosExistente = rol_permisosRepository.findById(rol_permisos.getIdrol_permisos()).orElseThrow();
	
			rol_permisosExistente.setIdrol(rol_permisos.getIdrol());
			rol_permisosExistente.setIdpermisos(rol_permisos.getIdpermisos());
			rol_permisosExistente.setCreadoridusuario(rol_permisos.getCreadoridusuario());
			
			Utils util = new Utils();
			rol_permisosExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Rol_permisos - update Rol_permisos() Method " +  rol_permisosExistente.toString());

			rol_permisosExistente = rol_permisosRepository.save(rol_permisosExistente);

			response = "El Rol_permisos " + rol_permisosExistente.getIdrol_permisos() + " ha sido actualizado exitosamente.";
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", rol_permisos.toString());
		}
		return response;
	}
}

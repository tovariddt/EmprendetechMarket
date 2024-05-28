package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Roles;
import com.emprendetech.market.repositorys.RolesRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.RolesDto;
import com.emprendetech.market.utils.Utils;

import lombok.Data;

@Data
@Controller
public class RolesController {

	private static final Log LOG = LogFactory.getLog(RolesController.class);

	@Autowired
	private RolesRepository rolesRepository;

	public String AltaRoles(@RequestBody RolesDto rolesDto) throws Exception {
		LOG.info("createAlta Roles- createAlta Roles() Method");
		LOG.debug("createAlta Roles:: " + rolesDto.toString());

		String response = null;
		try {
			Roles RolesInsert = new Roles();

			RolesInsert.setNombre(rolesDto.getNombre());
			RolesInsert.setDescripcion(rolesDto.getDescripcion());
			RolesInsert.setCreadoridusuario(rolesDto.getCreadoridusuario());

			Utils util = new Utils();
			RolesInsert.setFechacreacion(util.currentDate());
			RolesInsert.setFechamodificacion(util.currentDate());
			
			LOG.info("Alta Roles - Alta Roles() Method " +RolesInsert.toString());

			RolesInsert = rolesRepository.save(RolesInsert);

			response = "Felicidades Su Roles fue registrado como = " + RolesInsert.getNombre();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", rolesDto.toString());
		}
		return response;
	}
	
	public String actualizarRoles(@RequestBody Roles roles) throws Exception {
		LOG.info("update Roles - update Roles() Method");
		LOG.debug("update Roles :: " + roles.toString());

		String response = null;

		try {
			Roles rolesExistente = rolesRepository.findById(roles.getIdrol()).orElseThrow();

			rolesExistente.setNombre(roles.getNombre());
			rolesExistente.setDescripcion(roles.getDescripcion());
			rolesExistente.setCreadoridusuario(roles.getCreadoridusuario());

			Utils util = new Utils();
			rolesExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Roles - update Roles() Method " + rolesExistente.toString());

			rolesExistente = rolesRepository.save(rolesExistente);

			response = "El Roles " + rolesExistente.getNombre() + " ha sido actualizado exitosamente.";
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", roles.toString());
		}
		return response;
	}
}
